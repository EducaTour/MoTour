package com.dicoding.motour.data.preferences

import androidx.core.os.LocaleListCompat

object LanguageUtil {
    enum class ContentLanguage {
        English,
        Indonesia,
        Chinese
    }

    fun getLanguageTag(language: ContentLanguage): LocaleListCompat {
        return when (language) {
            ContentLanguage.English -> LocaleListCompat.forLanguageTags("en-US")
            ContentLanguage.Chinese -> LocaleListCompat.forLanguageTags("zh-Hans")
            ContentLanguage.Indonesia -> LocaleListCompat.forLanguageTags("in")
        }
    }

    fun getLanguageHeader(language: ContentLanguage): HashMap<String, String> {
        val header = HashMap<String, String>()

        header["Accept-Language"] = language.name

        return header
    }
}