package com.example.samplecomposetest.ui.screens.driverlicense.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.samplecomposetest.ui.screens.driverlicense.*
import com.example.samplecomposetest.ui.theme.GidTheme

class DriverLicenseDetailsComposeFragment : Fragment(){

    private val viewModel = DriverLicenseViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                GidTheme {
                    val obsState = viewModel.uiState.observeAsState()
                    obsState.value?.let { state ->
                        Crossfade(targetState = state) { currentState ->
                            when (currentState) {
                                is UiState.Loading -> {
                                    LoadingIndicator(Modifier.fillMaxSize())
                                }
                                is UiState.Success -> {
                                    DriverLicenseCardDetails(item = currentState.state,
                                        onBackClicked = {

                                        },
                                        onMoreBtnClicked = {

                                        })
                                }
                                UiState.ContentInProgress -> ContentInProgressWidget()
                                is UiState.Error -> {}
                                is UiState.Rejected -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}
