package com.yite.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    lateinit var b :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        R.id.tv_hello
        //b = "ada"
        Log.e("123",b)
    }

    fun fofo(a:String, b:String):String{
        var g:String? = null
        //g += b
        return a
    }
}
