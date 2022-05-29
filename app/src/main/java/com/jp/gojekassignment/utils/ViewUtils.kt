package com.jp.gojekassignment.utils

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
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

fun Resources.changeDrawableColor(drawableId: Int, color: Int): Drawable? {
    try {
        return ResourcesCompat.getDrawable(this, drawableId, null)?.apply { mutate()
                when (this) {
                    is ShapeDrawable -> {
                        paint.color = color
                    }
                    is ColorDrawable -> {
                        this.color = color
                    }
                    is GradientDrawable -> {
                        this.setColor(color)
                    }
                }
        }
    } catch (e: Exception) {
        notifyUserWithLog("Drawable $drawableId not found")
        return null
    }
}