package com.example.arkanoid

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.Display
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("ViewConstructor")
class ArkanoidView (context: Context, mode: String, screen: Display) : SurfaceView(context), Runnable {
    private var gameThread: Thread? = null
    private var ourHolder: SurfaceHolder = holder
    @Volatile
    var playing: Boolean = false
    private var paused = true
    private var canvas: Canvas? = null
    private var paint: Paint = Paint()
    private var fps: Long = 0
    private var timeThisFrame: Long = 0
    private var screenSizeX: Int = 0
    private var screenSizeY: Int = 0
    private var paddle: Paddle
    private var ball: Ball
    private var level : Int = 1
    private var nextLevel : Boolean = false
    private var bricks = arrayOfNulls<Brick>(100)
    private var numBricks = 0
    private var numOfBricksDown = 0
    private var brickColumn = 6
    private var brickRows = 3
    private var score = 0
    private var lives = 3

    init {
        val size = Point()
        screen.getSize(size)
        screenSizeX = size.x
        screenSizeY = size.y
        paddle = Paddle(screenSizeX, screenSizeY, mode)
        ball = Ball()
        prepareGame()
    }


    private fun prepareGame() {
        if(nextLevel && level < 6){
            score =+ level*100
            lives++
            level++
            brickColumn++
            brickRows++
        }
        if(level >= 6){
            playing = false
            canvas!!.drawText("YOU HAVE WON!", 10f, (screenSizeY / 2).toFloat(), paint)
            Timer().schedule(1000){
                val myintent = Intent(context, StartWindow::class.java)
                context.startActivity(myintent)
            }
        }

        ball.reset(screenSizeX, screenSizeY)
        val brickWidth = screenSizeX / brickColumn
        val brickHeight = screenSizeY / 22
        numBricks = 0
        numOfBricksDown = 0
        for (column in 0 until brickColumn) {
            for (row in 0 until brickRows) {
                bricks[numBricks] = Brick(row, column, brickWidth, brickHeight)
                numBricks++
            }
        }
    }

    override fun run() {
        while (playing) {
            val startFrameTime = System.currentTimeMillis()
            if (!paused) {
                updateGame()
            }
            draw()
            timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }
        }
    }
    private fun updateGame() {
        paddle.move(fps)
        ball.move(fps)
        for (i in 0 until numBricks) {
            if (bricks[i]!!.visibility) {
                if (RectF.intersects(bricks[i]!!.brickShape, ball.ballShape)) {
                    bricks[i]!!.setInvisible()
                    ball.changeYDirection()
                    score += 10
                    numOfBricksDown++
                }
            }
        }
        if (RectF.intersects(paddle.paddleShape, ball.ballShape)) {
            ball.changeYDirection()
        }
        if (ball.ballShape.bottom > screenSizeY) {
            ball.changeYDirection()
            lives--
        }
        if (ball.ballShape.top < 0) {
            ball.changeYDirection()
        }
        if (ball.ballShape.left < 0) {
            ball.changeXDirection()
        }
        if (ball.ballShape.right > screenSizeX) {
            ball.changeXDirection()
        }
        if (numOfBricksDown == numBricks ) {
            nextLevel = true
            paused = false
            lives++
            prepareGame()
        }
    }

    private fun draw() {
        if (ourHolder.surface.isValid) {
            canvas = ourHolder.lockCanvas()
            canvas!!.drawColor(Color.parseColor("#0F0326"))
            paint.color = Color.parseColor("#F5F7DC")
            canvas!!.drawRect(paddle.paddleShape, paint)
            canvas!!.drawRect(ball.ballShape, paint)
            paint.color = Color.parseColor("#E65F5C")

            for (i in 0 until numBricks) {
                if (bricks[i]!!.visibility) {
                    canvas!!.drawRect(bricks[i]!!.brickShape, paint)
                }
            }

            paint.color = Color.parseColor("#F5F7DC")
            paint.textSize = 50f
            canvas!!.drawText("Level: $level   Score: $score   Lives: $lives", 10f, 50f, paint)

            paint.color = Color.parseColor("#6D676E")

            if (lives <= 0) {
                paint.textSize = 90f
                playing = false
                canvas!!.drawText("YOU HAVE LOST!", 10f, (screenSizeY / 2).toFloat(), paint)
                Timer().schedule(1000){
                    val myintent = Intent(context, StartWindow::class.java)
                    context.startActivity(myintent)
                }
            }
            ourHolder.unlockCanvasAndPost(canvas)
        }
    }
    fun pause() {
        playing = false
        try {
            gameThread!!.join()
        } catch (e: InterruptedException) {
        }

    }
    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                paused = false
                if (motionEvent.x > screenSizeX / 2) {
                    paddle.setMovementState(paddle.right)
                } else {
                    paddle.setMovementState(paddle.left)
                }
            }
            MotionEvent.ACTION_UP ->
                paddle.setMovementState(paddle.stopped)
        }
        return true
    }

}