package com.company.metrix.swipe.behaviour

import android.view.View
import com.company.metrix.swipe.QuickActionsStates
import com.company.metrix.swipe.utils.Size

internal class NoOpBehaviourDelegate: BehaviourDelegate {

    override fun layoutAction(view: View, l: Int, r: Int, actionSize: Size) {
    }

    override fun clampViewPosition(parentView: View, view: View, left: Int, actionSize: Size): Int {
        return 0
    }

    override fun translateAction(mainView: View, actionView: View, actionSize: Size, dx: Int, index: Int) {
    }

    override fun isOpened(position: Int, actionSize: Size): Boolean {
        return false
    }

    override fun getFinalLeftPosition(view: View, velocity: Float, actionSize: Size): Int {
        return 0
    }

    override fun getStateForPosition(
        view: View,
        actionSize: Size
    ): QuickActionsStates {
        return QuickActionsStates.CLOSED
    }

    override fun getPositionForState(view: View, actionSize: Size, states: QuickActionsStates): Int {
        return 0
    }
}