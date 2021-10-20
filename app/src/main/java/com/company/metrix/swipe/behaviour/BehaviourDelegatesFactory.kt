package com.company.metrix.swipe.behaviour

import android.content.Context
import com.company.metrix.swipe.SwipeToActionLayout
import com.company.metrix.swipe.utils.isLtr

internal class BehaviourDelegatesFactory(
    private val context: Context
) {

    fun create(actionCount: Int, gravity: SwipeToActionLayout.MenuGravity, isFullActionSupported: Boolean): BehaviourDelegate {
        return when(gravity) {
            SwipeToActionLayout.MenuGravity.LEFT -> createLeftDelegate(actionCount, isFullActionSupported)
            SwipeToActionLayout.MenuGravity.RIGHT -> createRightDelegate(actionCount, isFullActionSupported)
            SwipeToActionLayout.MenuGravity.START -> if (isLtr()) createLeftDelegate(actionCount, isFullActionSupported) else createRightDelegate(actionCount, isFullActionSupported)
            SwipeToActionLayout.MenuGravity.END -> if (isLtr()) createRightDelegate(actionCount, isFullActionSupported) else  createLeftDelegate(actionCount, isFullActionSupported)
        }
    }

    private fun createLeftDelegate(actionCount: Int, isFullActionSupported: Boolean): BehaviourDelegate {
        return if (isFullActionSupported) {
            FullLeftDirectedBehaviorDelegate(actionCount, context)
        } else {
            LeftDirectedBehaviourDelegate(actionCount, context)
        }
    }

    private fun createRightDelegate(actionCount: Int, isFullActionSupported: Boolean): BehaviourDelegate {
        return if (isFullActionSupported) {
            FullRightDirectedBehaviorDelegate(actionCount, context)
        } else {
            RightDirectedBehaviourDelegate(actionCount, context)
        }
    }

}