package com.example.arkanoid

import android.graphics.RectF

class Brick(row: Int, column: Int, width: Int, height: Int) {

    val brickShape: RectF
    var visibility: Boolean = false

    init {
        visibility = true
        val padding = 1
        brickShape = RectF(
            (column * width + padding).toFloat(),
            (row * height + padding).toFloat(),
            (column * width + width - padding).toFloat(),
            (row * height + height - padding).toFloat()
        )
    }

    fun setInvisible() {
        visibility = false
    }
}