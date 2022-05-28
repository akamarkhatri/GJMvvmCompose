package com.jp.gojekassignment.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jp.gojekassignment.data.model.git.RepoOwner

class RoomDataTypeConvertor {
    @TypeConverter
    fun convertRepoOwnerToString(repoOwner: RepoOwner?): String? {
        repoOwner?:return null
        val type=object : TypeToken<ArrayList<RepoOwner>>(){}.type
        return Gson().toJson(repoOwner,type)
    }

    @TypeConverter
    fun convertStringToRepoOwner(value:String?):RepoOwner? {
        value ?: return null
        val type = object : TypeToken<RepoOwner>(){}.type
        return Gson().fromJson(value, type)
    }
}