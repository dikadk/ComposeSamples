package com.example.samplecomposetest.ui.screens.driverlicense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class DriverLicenseViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    init {
        val item = getDlPreviewState()
        _uiState.value = UiState.Success(item, false)
    }
}
