package com.example.canvascraft.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.canvascraft.MainActivity
import com.example.canvascraft.MainActivity.Companion.currentBrush
import com.example.canvascraft.shapes.Ellipse

class DrawEllipse @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val ellipse = mutableListOf<Ellipse>()

    private val paintBrush = Paint().apply {
        strokeWidth = 10f
        color = currentBrush
        isAntiAlias = true
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                MainActivity.colorList.add(MainActivity.currentBrush)
                ellipse.add(Ellipse(event.x, event.y, event.x, event.y, currentBrush))
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val current = ellipse[ellipse.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val current = ellipse[ellipse.size - 1]
                current.stopX = event.x
                current.stopY = event.y
                invalidate()
                return true
            }

            else -> {
                return false
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        for (e in ellipse) {
            paintBrush.color = e.color
            canvas.drawOval(e.startX, e.startY, e.stopX, e.stopY, paintBrush)
        }
    }

}