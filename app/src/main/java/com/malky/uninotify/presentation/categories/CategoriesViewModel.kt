package com.malky.uninotify.presentation.categories

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.domain.core.EventCategory
import com.malky.uninotify.domain.core.EventType
import com.malky.uninotify.domain.data.EventsRepository
import com.malky.uninotify.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val eventsRepo : EventsRepository
) : ViewModel() {
    val categories = mutableStateMapOf<EventType, Int>(
        EventType.Seminar to 0,
        EventType.Exam to 0,
        EventType.Fest to 0,
        EventType.Notice to 0
    )

    init {
        viewModelScope.launch{
            eventsRepo.selectEventsCountGroupByType()
                .onSuccess { result ->
                    result.forEach { (key,value) ->
                        categories[key] = value
                    }
                }
        }
    }

}