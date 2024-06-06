package com.dicoding.motour.data.db

import androidx.room.TypeConverter
import com.dicoding.motour.data.model.StringList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    @TypeConverter
    fun fromString(value: String?): StringList? {
        val listType = object : TypeToken<List<String>>() {}.type
        return if (value == null) null else StringList(Gson().fromJson(value, listType))
    }

    @TypeConverter
    fun fromList(list: StringList?): String? {
        return Gson().toJson(list?.strings)
    }
}
