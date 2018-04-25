package com.example.martin.personalenergytrainer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.martin.personalenergytrainer.retrofit.APIRelated
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfile : AppCompatActivity() {

    lateinit var firstname: EditText
    lateinit var surname: EditText
    lateinit var address: EditText
    lateinit var btn_save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        btn_save = findViewById<View>(R.id.btnSaveProfile) as Button
        firstname = findViewById(R.id.txtFirstNameUser)
        surname = findViewById(R.id.txtSurnameUser)
        address = findViewById(R.id.txtAddressUser)

        btn_save.setOnClickListener {
            var name1 = firstname.text.toString()
            var name2 = surname.text.toString()
            var address = address.text.toString()

            save_profile(name1, name2, address)
            Toast.makeText(applicationContext, "The values have been saved.", Toast.LENGTH_SHORT).show()
        }
    }


    //save the data
    fun save_profile(name1: String, name2: String, address: String){
        val jsonObj = JsonObject()
        jsonObj.addProperty("firstname", name1) //change this line
        jsonObj.addProperty("surname", name2) //change this line too
        jsonObj.addProperty("address", address) //the string to be saved!

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
