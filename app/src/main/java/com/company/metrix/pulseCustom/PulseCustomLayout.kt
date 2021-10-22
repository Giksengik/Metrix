package com.company.metrix.pulseCustom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.company.metrix.R

class PulseCustomLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttrs, defStyleRes) {

    private val numQuestion: TextView // TODO rounded img view
    private var lenBar: TextView //TODO ?
    private val numRect = Rect()
    private val messageRect = Rect()

    var percent = 0

    init {

        val typedArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.PulseCustomLayout,
            defStyleAttrs,
            defStyleRes
        )

        percent = typedArray.getInteger(R.styleable.PulseCustomLayout_percent, percent)

        LayoutInflater.from(context).inflate(R.layout.pulse_custom_view, this, true)
        numQuestion = findViewById(R.id.bar)
        lenBar = findViewById(R.id.num)
        lenBar.setTextColor(resources.getColor(R.color.white))
        //  background = resources.getDrawable(R.drawable.bg_pulse_rect)
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
        val totalHeight = lenBar.height() + numQuestion.height() + 50

        setMeasuredDimension(
            resolveSize(totalWidth, widthMeasureSpec),
            resolveSize(
                totalHeight,
                totalWidth
            )
        )
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        numQuestion.layout(numQuestion.rect(numRect, 0, 20))
        val rect = lenBar.rect(messageRect, numQuestion.right, 0, r*percent/100)
        lenBar.layout(
            rect
        )
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams =
        MarginLayoutParams(context, attrs)


}