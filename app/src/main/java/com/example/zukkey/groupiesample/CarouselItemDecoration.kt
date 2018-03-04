package com.example.zukkey.groupiesample

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.view.View


class CarouselItemDecoration(@ColorInt backgroundColor: Int, private val padding: Int) : RecyclerView.ItemDecoration() {

    private val grayBackgroundPaint: Paint = Paint()

    init {
        grayBackgroundPaint.color = backgroundColor
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.right = padding
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val lm = parent.layoutManager
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            var right = (lm.getDecoratedRight(child) + child.translationX)
            if (i == childCount - 1) {
                // Last item
                right = Math.max(right, parent.width.toFloat())
            }

            // Right border
            c.drawRect(child.right + child.translationX, 0.0f, right, parent.height.toFloat(), grayBackgroundPaint)
        }
    }
}