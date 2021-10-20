package com.company.metrix.swipe.events

import com.company.metrix.swipe.QuickActionsStates


internal class QuickActionsMenuStateProcessor {
    // TODO почему все может быть null? Потенциальная дыра для ошибок, нужно юзать lateinit
    var onProgressiveStateChanged: ((state: QuickActionsStates) -> Unit)? = null
    var onReleaseStateChanged: ((state: QuickActionsStates) -> Unit)? = null

    private var state: QuickActionsStates? = null

    fun setState(state: QuickActionsStates) {
        val oldState = this.state
        val newState = state

        if (oldState != newState) {

            onProgressiveStateChanged?.invoke(newState)
            this.state = newState
        }
    }

    fun release() {
        state?.let { onReleaseStateChanged?.invoke(it) }
    }

}
