package com.example.api

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operation = "simplify"
        retrofit = Retrofit.Builder()
            .baseUrl("https://newton.now.sh")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun operationButton(view: View){
        val buttonId = view.id
        var myButton = findViewById<Button>(buttonId)
        operation = myButton.text.toString()

    }

    fun clickSend(view: View) {
        val equation = input.textInput.text.toString()
        if (equation.isNotEmpty() && equation.isNotBlank()) {

            if (operation == "zeroes") {
                val newton = retrofit.create(NewtonZeroAPI::class.java)
                val call = newton.resolve(operation, equation)
                call.enqueue(object : Callback<NewtonZeroCallBack> {
                    override fun onResponse(call: Call<NewtonZeroCallBack>, response: Response<NewtonZeroCallBack>) {
                        val body = response.body()
                        if (body?.result != null) {
                            var response = "{"
                            for (number: Double? in body.result) {
                                if (number != null) {
                                    response += "$number, "
                                }
                            }
                            response += "}"
                            textView.text = response
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFailure(call: Call<NewtonZeroCallBack>, t: Throwable) {
                        Log.d("am2019", "something went wrong")
                        textView.text = "something went wrong, please try again"
                    }
                })
            } else {
                val newton = retrofit.create(NewtonAPI::class.java)
                val call = newton.resolve(operation, equation)
                call.enqueue(object : Callback<NewtonCallBack> {
                    override fun onResponse(call: Call<NewtonCallBack>, response: Response<NewtonCallBack>) {
                        var body = response.body()
                        textView.text = body!!.result
                        Log.d("am2019", body.result)
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFailure(call: Call<NewtonCallBack>, t: Throwable) {
                        Log.d("am2019", "something went wrong")
                        textView.text = "something went wrong, please try again"
                    }
                })
            }

        }
    }
}
