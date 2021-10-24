package com.company.metrix.pulseCustom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.company.metrix.R

class PulseCustomLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttrs, defStyleRes) {

    private val numQuestion: RoundedImageView
    private var lenBar: TextView //TODO ?
    private val numRect = Rect()
    private val messageRect = Rect()

    var percent = 0
    set(value) {
        field = value
        requestLayout()
    }

    var icon = R.drawable.ic_first_choice
    set(value) {
        numQuestion.setImageDrawable(
            AppCompatResources.getDrawable(context, value))

        field = value
    }

    init {
        val typedArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.PulseCustomLayout,
            defStyleAttrs,
            defStyleRes
        )
        LayoutInflater.from(context).inflate(R.layout.pulse_custom_view, this, true)

        percent = typedArray.getInteger(R.styleable.PulseCustomLayout_percent, percent)

        numQuestion = findViewById(R.id.num)
        numQuestion.setImageDrawable(
            AppCompatResources.getDrawable(context, icon))

        lenBar = findViewById(R.id.bar)
        lenBar.setTextColor(resources.getColor(R.color.white))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        measureChildWithMargins(
            numQuestion,
            widthMeasureSpec,
            0,
            heightMeasureSpec,
            0
        )
        measureChildWithMargins(
            lenBar,
            widthMeasureSpec,
            0,
            heightMeasureSpec,
            0
        )

        val totalWidth = numQuestion.width() + 200
        val totalHeight = lenBar.height()

        setMeasuredDimension(
            resolveSize(totalWidth, widthMeasureSpec),
            resolveSize(
                totalHeight,
                totalWidth
            )
        )
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val rect = lenBar.rect(messageRect, 0, 0, r * percent / 100)
        numQuestion.layout(numQuestion.rect(numRect, 0, 20))
        lenBar.layout(
            rect
        )
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams =
        MarginLayoutParams(context, attrs)


}