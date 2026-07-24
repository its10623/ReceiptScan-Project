package com.smoothsm.cameraapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Intent : UiIntent, State : UiState, SideEffect : UiSideEffect> : ViewModel() {
    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(createInitialState())
    val uiState = _uiState.asStateFlow()

    private val _uiIntent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val uiIntent = _uiIntent.asSharedFlow()

    private val _uiSideEffect: Channel<SideEffect> = Channel()
    val uiSideEffect = _uiSideEffect.receiveAsFlow()

    init {
        subscribeIntents()
    }

    fun handleIntent(intent: Intent) {
        viewModelScope.launch { _uiIntent.emit(intent) }
    }

    protected fun setState(reduce: State.() -> State) {
        _uiState.value = currentState.reduce()
    }

    protected fun setSideEffect(builder: () -> SideEffect) {
        viewModelScope.launch { _uiSideEffect.send(builder()) }
    }

    private fun subscribeIntents() {
        viewModelScope.launch {
            uiIntent.collect {
                onIntent(it)
            }
        }
    }

    abstract fun onIntent(intent: Intent)
}
