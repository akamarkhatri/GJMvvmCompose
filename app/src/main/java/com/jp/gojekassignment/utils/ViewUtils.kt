package com.jp.gojekassignment.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.jp.gojekassignment.R

fun AppCompatActivity.showToast(msg: String) {
    android.widget.Toast.makeText(this, msg, android.widget.Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showSnackbar(
    view: View?,
    msg: String,
    actionValue: String = "",
    actionCallback: ((view: View) -> Unit)? = null
) {
    view ?: return showToast(msg)
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    if (android.text.TextUtils.isEmpty(actionValue)) return
    Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE).setAction(actionValue, actionCallback)
        .show()
}

fun AppCompatActivity.showCheckYourInternetMsg() {
    showToast(getString(R.string.msg_check_internet))
}

fun ImageView.loadThumbnailImage(
    imageUrl: String?,
    placeHolder_id: Int = R.drawable.default_repo_icon
) {
    Glide.with(this)
        .asBitmap()
        .apply(RequestOptions.placeholderOf(placeHolder_id))
        .load(imageUrl ?: "")
        .thumbnail(
            Glide.with(this)
                .asBitmap().sizeMultiplier(0.1f)
        )
        .into(this)
}