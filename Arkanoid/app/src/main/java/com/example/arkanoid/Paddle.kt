package com.example.arkanoid
import android.graphics.RectF

class Paddle (screenX: Int, screenY: Int, mode: String) {

    val paddleShape: RectF

    private val length: Float
    private val height: Float = 30f
    private val myscreenX = screenX

    private var x: Float = 0.toFloat()
    private val y: Float

    private val paddleSpeed: Float

    val stopped = 0
    val left = 1
    val right = 2


    private var paddleMoving = stopped

    init {
        when (mode) {
            "easy" -> {
                length = 200f
                paddleSpeed = 350f
            }
            "medium" -> {
                length = 150f
                paddleSpeed = 450f
            }
                else -> {
                length = 100f
                paddleSpeed = 550f
            }
        }
        x = (screenX / 2).toFloat()
        y = (screenY - 250).toFloat()

        paddleShape = RectF(x, y, x + length, y + height)


    }

    fun setMovementState(direction: Int) {
        paddleMoving = direction
    }

    fun move(fps: Long) {
        if (paddleMoving == left) {
            if(x - paddleSpeed / fps > 0)
                x -= paddleSpeed / fps
        }

        if (paddleMoving == right) {
            if(x + paddleSpeed / fps < myscreenX-length)
                x += paddleSpeed / fps
        }

        paddleShape.left = x
        paddleShape.right = x + length
    }

}