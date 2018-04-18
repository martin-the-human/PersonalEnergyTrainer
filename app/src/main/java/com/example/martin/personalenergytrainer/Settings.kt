package com.example.martin.personalenergytrainer

//from https://github.com/Audhil/FlaskWithAndroid
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.martin.personalenergytrainer.retrofit.APIRelated
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_settings.* //save
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.widget.RadioButton
import android.widget.Toast

public class Settings : AppCompatActivity(){

    var colour: Int = 0
    var time: Int = 0

    //either use "lateinit var" or "var x: Type? = null"
    lateinit var radio_12: RadioButton
    lateinit var radio_24: RadioButton

    lateinit var blue: RadioButton
    lateinit var pink: RadioButton
    lateinit var white: RadioButton


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings) //change this to the right view?

        radio_12 = findViewById(R.id.rad_12hr)
        radio_24 = findViewById(R.id.rad_24hr)

        blue = findViewById(R.id.rad_blue)
        pink = findViewById(R.id.rad_pink)
        white = findViewById(R.id.rad_white)


        //when time buttons are pressed
        rad_grp_time.setOnCheckedChangeListener { radioGroup, i ->
            time_format()
            toast("Time format has been changed")
        }

        //when shirt buttons are pressed
        rad_grp_shirt.setOnCheckedChangeListener { radioGroup, i ->
            shirt_colour()
            toast("Colour has been changed!")
        }

        // save new settings to db
        btnSaveSettings.setOnClickListener{
            save()
            toast("Saved!")
            Toast.makeText(applicationContext, colour.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    //call toast function
    fun toast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    fun time_format() {
        //1 = 12hr, 2 = 24hr, 3 = nothing checked
        if (radio_12.isChecked) {
            time = 1
        } else if (radio_24.isChecked) {
            time = 2
        } else if (!radio_12.isChecked && !radio_24.isChecked) {
            time = 3
        }
    }

    fun shirt_colour() {
        // 1 = pink, 2 = white, 3 = blue/default
        if (pink.isChecked) {
            colour = 1
        } else if (white.isChecked) {
            colour = 2
        } else if (blue.isChecked) {
            colour = 3
        } else if (!pink.isChecked && !white.isChecked && !blue.isChecked) {
            colour = 3
        }
    }

    fun save()
    {
        val jsonObj = JsonObject()
        jsonObj.addProperty("title", "rhythm") //change this line
        jsonObj.addProperty("singer", "me") //change this line too
        jsonObj.addProperty("text", "hello there") //the string to be saved!

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