package com.yite.guess

import android.util.Log
import java.util.*

class SecretNumber {
    val secret = Random().nextInt(10) + 1
    var count = 0

    fun validate(number :Int):Int{
        count++
        return number - secret
    }

    fun printNumber(){
        //val secretNumber = SecretNumber()
        Log.e("123", secret.toString())
        Log.e("123","${secret} , count:${count}")
    }
}