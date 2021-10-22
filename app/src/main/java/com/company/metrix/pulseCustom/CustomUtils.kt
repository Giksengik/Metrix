package com.company.metrix.pulseCustom

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup


fun View.width(): Int {
    val margins = this.layoutParams as ViewGroup.MarginLayoutParams
    return this.measuredWidth + margins.leftMargin + margins.rightMargin
}

fun View.height(): Int {
    val margins = this.layoutParams as ViewGroup.MarginLayoutParams
    return this.measuredHeight + margins.topMargin + margins.bottomMargin
}

fun View.layout(rect: Rect) = layout(rect.left, rect.top, rect.right, rect.bottom)

fun View.rect(rect: Rect, l: Int, t: Int): Rect {
    val viewLayoutParams: ViewGroup.MarginLayoutParams =
        this.layoutParams as ViewGroup.MarginLayoutParams

    rect.apply {
        left = l + viewLayoutParams.leftMargin
        top = t + viewLayoutParams.topMargin
        right = rect.left + measuredWidth + viewLayoutParams.rightMargin
        bottom = rect.top + measuredHeight + viewLayoutParams.bottomMargin
    }
    return rect
}

fun View.rectLeft(rect: Rect, r: Int, t: Int): Rect {
    val viewLayoutParams: ViewGroup.MarginLayoutParams =
        this.layoutParams as ViewGroup.MarginLayoutParams

    rect.apply {
        left = rect.right - measuredWidth - viewLayoutParams.leftMargin
        top = t + viewLayoutParams.topMargin
        right = r - viewLayoutParams.rightMargin
        bottom = rect.top + measuredHeight + viewLayoutParams.bottomMargin
    }
    return rect
}

fun View.rect(
    rect: Rect,
    leftBorder: Int,
    topBorder: Int,
    rightBorder: Int
): Rect {
    val viewLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams

    viewLayoutParams.also {
        rect.apply {
            left = leftBorder + it.leftMargin
            top = topBorder + it.topMargin
            right = rightBorder + it.rightMargin
            bottom = top + measuredHeight + it.bottomMargin
        }
    }
    return rect
}

