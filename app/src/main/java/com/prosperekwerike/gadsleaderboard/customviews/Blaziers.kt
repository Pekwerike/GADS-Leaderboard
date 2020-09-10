package com.prosperekwerike.gadsleaderboard.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.prosperekwerike.gadsleaderboard.R

class Blaziers @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mHeight = 0
    private var mWidth = 0

    private val paint = Paint().apply {
        isDither = true
        isAntiAlias = true
        color = resources.getColor(R.color.darken_ray)
        style = Paint.Style.FILL
    }

   private var topPath : Path = Path()
   private var bottomPath : Path = Path()

    private fun initializePaths() {
         topPath = Path().apply {
            moveTo(0F, 0F)
            lineTo(0F, mHeight.toFloat() * 0.25F)
            quadTo(mWidth / 2f, mHeight / 4.5f, mWidth.toFloat() * 0.35F, 0F)
            lineTo(0F, 0F)
        }
         bottomPath = Path().apply {
            moveTo(0F, mHeight.toFloat())
            lineTo(mWidth.toFloat(), mHeight.toFloat())
            lineTo(mWidth.toFloat(), mHeight / 1.5f)
            quadTo(mWidth / 3f, mHeight / 1.3f, 0F, mHeight.toFloat() * 0.95F)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBottomCurvedPath(canvas)
        drawTopCurvedPath(canvas)
    }

    private fun drawTopCurvedPath(canvas: Canvas?) {
        canvas!!.drawPath(topPath, paint)
    }

    private fun drawBottomCurvedPath(canvas: Canvas?) {
        canvas!!.drawPath(bottomPath, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)

        initializePaths()
    }


}