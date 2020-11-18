package com.yite.guess

import android.util.Log
import java.util.*
import java.lang.Character.isDigit as isDigit1

class SecretNumber {
    var secret = Random().nextInt(10) + 1
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

    fun reset(){
        secret = Random().nextInt(10) + 1
        count = 0
    }

    fun checkNumber(number:String):Boolean{
        if(number.isEmpty()){
            return false
        }
        for(numberChar:Char in number){
            if (!isDigit1(numberChar)){
                return false
            }
        }
        return true
    }
}