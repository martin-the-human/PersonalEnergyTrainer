package com.example.martin.personalenergytrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
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
    lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        username = findViewById(R.id.editTextLoginEmail)
        password = findViewById(R.id.editTextLoginPassword)
        btn_login = findViewById<View>(R.id.buttonLogin) as Button

        btn_login.setOnClickListener {
            var un = username.text.toString()
            var pw = password.text.toString()

            val mainActivity = Intent(this@LoginScreen, MainActivity::class.java)
            val user_info = Bundle()
            user_info.putString("username", un)
            user_info.putString("password", pw)

            mainActivity.putExtras(user_info)

            startActivity(mainActivity)
            //find_details(un, pw)
        }
    }

    //send login details to the server to load the accounts details
    fun find_details(un: String, pw:String) {
        val jsonObj = JsonObject()
        jsonObj.addProperty("username", un) //change this line
        jsonObj.addProperty("password", pw) //change this line too

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

    //test account
    fun test_data(un: String, pw: String) {
        //show that edittext can be converted to string
        Toast.makeText(applicationContext, un, Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, pw, Toast.LENGTH_SHORT).show()

    }

}
