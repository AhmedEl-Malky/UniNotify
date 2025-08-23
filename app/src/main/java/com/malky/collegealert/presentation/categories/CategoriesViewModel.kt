package com.malky.collegealert.presentation.categories

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoriesViewModel : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow()

    fun onAction(action: CategoriesAction) {
        when (action) {
            else -> {}
        }
    }

}