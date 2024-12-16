package org.sopt.and.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface UiState
interface UiSideEffect
interface UiEvent

abstract class BaseViewModel<State : UiState, SideEffect : UiSideEffect, Event : UiEvent>(
) : ViewModel() {
    private val initState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow<State>(initState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> = MutableSharedFlow<SideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun setState(reduce: State.() -> State) {
        _uiState.value = uiState.value.reduce()
    }

    open fun setEvent(event: Event) = dispatchEvent(event)

    private fun dispatchEvent(event: Event) = viewModelScope.launch {
        handleEvent(event)
    }

    protected abstract suspend fun handleEvent(event: Event)

    fun setSideEffect(effect: SideEffect) = viewModelScope.launch {
        _sideEffect.emit(effect)
    }
}
