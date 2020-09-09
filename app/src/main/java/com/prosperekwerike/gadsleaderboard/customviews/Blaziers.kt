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

    val paint = Paint().apply {
        isDither = true
        isAntiAlias = true
        color = resources.getColor(R.color.darken_ray)
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBottomCurvedPath(canvas)
        drawTopCurvedPath(canvas)
    }

    private fun drawTopCurvedPath(canvas: Canvas?){
        val path = Path().apply {
            moveTo(0F, 0F)
            lineTo(0F, height.toFloat() * 0.25F)
            quadTo(width/2f, height/4.5f, width.toFloat() * 0.35F, 0F)
            lineTo(0F, 0F)
        }

        canvas!!.drawPath(path, paint)
    }

    private fun drawBottomCurvedPath(canvas : Canvas?){
        val path = Path().apply {
            moveTo(0F, height.toFloat())
            lineTo(width.toFloat(), height.toFloat())
            lineTo(width.toFloat(), height/1.6f)
            quadTo(width/3f, height/1.4f, 0F, height.toFloat() * 0.94F)
        }

        canvas!!.drawPath(path, paint)
    }


}