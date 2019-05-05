package com.example.arkanoid
import android.graphics.RectF


class Ball {
    var ballShape: RectF
    private var xDirection: Float = 0.toFloat()
    private var yDirection: Float = 0.toFloat()
    private var ballWidth = 20f
    private var ballHeight = 20f

    init {
        xDirection = 150f
        yDirection = -500f
        ballShape = RectF()
    }

    fun move(fps: Long) {
        ballShape.left = ballShape.left + xDirection / fps
        ballShape.top = ballShape.top + yDirection / fps
        ballShape.right = ballShape.left + ballWidth
        ballShape.bottom = ballShape.top - ballHeight
    }

    fun changeYDirection() {
        yDirection = -yDirection
    }

    fun changeXDirection() {
        xDirection = -xDirection
    }

    fun reset(x: Int, y: Int) {
        ballShape.left = (x / 2).toFloat()
        ballShape.top = (y - 20).toFloat()
        ballShape.right = (x / 2 + ballWidth)
        ballShape.bottom = y.toFloat() - 20f - ballHeight
    }

}