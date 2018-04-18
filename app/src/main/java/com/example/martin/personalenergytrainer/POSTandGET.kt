package com.example.martin.personalenergytrainer

//from https://github.com/Audhil/FlaskWithAndroid
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.martin.personalenergytrainer.retrofit.APIRelated
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import kotlinx.android.synthetic.main.postget_test.* //this is required for the layout used

public class POSTandGET : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.postget_test)

        // to save to db
        btn_POST.setOnClickListener{
            save()
        }

        //get data from db
        btn_GET.setOnClickListener{
            load()
        }

    }

    //call toast function
    fun toast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
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

    fun load()
    {
        APIRelated
                .service
                .greetUser("Gandtix") //change to what you want
                .enqueue(object: Callback<ResponseBody>{
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable){
                        println("---TTTT :: GET Throwable EXCEPTION:: "+ t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>){
                        if (response.isSuccessful){
                            val msg = response.body()?.string()
                            println("---TTTT :: GET msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                        }
                        else{
                            println("didn't work")
                        }
                    }
                })
    }

}