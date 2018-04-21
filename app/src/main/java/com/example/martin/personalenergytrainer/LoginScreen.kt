package com.example.martin.personalenergytrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.martin.personalenergytrainer.retrofit.APIRelated
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.android.synthetic.main.activity_login_screen.*
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.net.URL

class LoginScreen : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val btn_login = findViewById<View>(R.id.buttonLogin) as Button
        btn_login.setOnClickListener {
            val mainActivity = Intent(this@LoginScreen, MainActivity::class.java)
            startActivity(mainActivity)
        }
    }

    //send login details to the server and load the accounts details
    fun login_save() {
        val jsonObj = JsonObject()
        jsonObj.addProperty("username", "rhythm") //change this line
        jsonObj.addProperty("password", "me") //change this line too

        //POST demo
        APIRelated
                .service
                .getVectors(jsonObj)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION::" + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }

}
