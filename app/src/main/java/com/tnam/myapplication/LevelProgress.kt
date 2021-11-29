package com.tnam.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.ColorUtils
import kotlin.math.min

class LevelProgress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 50

    companion object {

        const val DEFAULT_SPEED = 1F
        const val DEFAULT_TEXT_TITLE = "Level"
        const val DEFAULT_START_ANGLE = 120
        const val DEFAULT_TOTAL_ANGLE = 300
        const val DEFAULT_STROKE_WiDTH = 10
        const val OPACITY = 100
        const val DEFAULT_MAX_PROGRESS = 100
        const val DEFAULT_TEXT_LEVEL_COLOR = Color.WHITE
        const val DEFAULT_TEXT_TITLE_COLOR = Color.BLACK
        const val DEFAULT_BACKGROUND_COLOR = Color.GREEN
        const val DEFAULT_UNPROGRESS_COLOR = Color.GRAY
        const val DEFAULT_IS_ENABLE = true
        const val DEFAULT_IS_STEP_PROGRESS = false

    }

    private val borderRect = RectF()
    private var continuousSwipeAngle = 0f
    private var continuousStartAngle = 0f
    private var speed = DEFAULT_SPEED
    private var progress = 0
    private var angle = 0F
    private var textOffset = 0F

    private var progressPaint = Paint()
    private var unProgressPaint = Paint()
    private var textLevelPaint = Paint()
    private var textTitlePaint = Paint()
    private val backgroundPaint = Paint()
    private val paintCircle = Paint().apply {

    }

    init {
        borderRect.set(getBoundRectF(10F))
        progressPaint.apply {
            isAntiAlias = true
            color = ColorUtils.blendARGB(Color.GREEN, Color.BLACK, 0.2f)
            style = Paint.Style.STROKE
            strokeWidth = strokeWidth
            Paint.Cap.ROUND
        }

        unProgressPaint.apply {
            isAntiAlias = true
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 2F
            Paint.Cap.ROUND

        }
    }

    var step = 3

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        while (step <= 45) {
            if (step <= angle) {
                canvas?.drawArc(
                    RectF(10F, 10F, (width - 10).toFloat(), (height - 10).toFloat()),
                    (DEFAULT_START_ANGLE + step).toFloat(),
                    10f,
                    false,
                    progressPaint
                )
            } else {
                canvas?.drawArc(
                    RectF(10F, 10F, (width - 10).toFloat(), (height - 10).toFloat()),
                    270F + step,
                    1f,
                    false,
                    unProgressPaint
                )
            }
            step += 5
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth = (width + 10F + paddingRight + paddingLeft).toInt()
        val desiredHeight = (height + 10F + paddingTop + paddingBottom).toInt()

        val width: Int
        val height: Int

        //Measure Width
        width = when (widthMode) {
            MeasureSpec.EXACTLY -> //Must be this size
                widthSize
            MeasureSpec.AT_MOST -> //Can't be bigger than...
                min(desiredWidth, widthSize)
            else -> //Be whatever you want
                desiredWidth
        }

        //Measure Height
        height = when (heightMode) {
            MeasureSpec.EXACTLY -> //Must be this size
                heightSize
            MeasureSpec.AT_MOST -> //Can't be bigger than...
                min(desiredHeight, heightSize)
            else -> //Be whatever you want
                desiredHeight
        }
        val min = min(width, height)
        radius = (min / 2.5).toInt()

        setMeasuredDimension(width, height)

    }

}