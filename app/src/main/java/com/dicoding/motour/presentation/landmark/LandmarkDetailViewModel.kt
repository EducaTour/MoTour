package com.dicoding.motour.presentation.landmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.motour.data.model.landmark.detail.LandmarkDetail
import com.dicoding.motour.data.preferences.LanguagePreference
import com.dicoding.motour.data.preferences.LanguageUtil
import com.dicoding.motour.domain.usecase.GetLandmarkByIdUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkByIdUseCase
import kotlinx.coroutines.flow.first

class LandmarkDetailViewModel(
    private val getLandmarkByIdUseCase: GetLandmarkByIdUseCase,
    private val updateLandmarkByIdUseCase: UpdateLandmarkByIdUseCase,
    private val languagePreference: LanguagePreference
) : ViewModel() {

    private val _data = MutableLiveData<Any>()
    val landmark: LiveData<Any> get() = _data

    sealed class LandmarkDetailState<out R> {
        data object Loading : LandmarkDetailState<Nothing>()
        data class Result(val landmark: LandmarkDetail?) : LandmarkDetailState<LandmarkDetail?>()
        data class Error(val error: String) : LandmarkDetailState<String>()
    }

    suspend fun getLandmarkById(id: Int) {
        val language = LanguageUtil.ContentLanguage.entries[languagePreference.languageId.first()]
        var landmark: LandmarkDetail? = null
        _data.value = LandmarkDetailState.Loading

        try {
            landmark = getLandmarkByIdUseCase.execute(id, language)
            _data.value = LandmarkDetailState.Result(landmark)
        } catch (e: Exception) {
            _data.value = LandmarkDetailState.Error(e.message.toString())
        }
    }

    fun updateLandmarkById(id: Int) = liveData {
        val landmark = updateLandmarkByIdUseCase.execute(id)
        emit(landmark)
    }
}