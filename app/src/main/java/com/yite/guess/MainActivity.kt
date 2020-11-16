package com.yite.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var secretNumber = SecretNumber()
    var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        secretNumber.printNumber()
    }

    fun check(view:View){
        if(edt_number.text.toString()!=null) {
            var n = edt_number.text.toString().toInt()
            Log.e("number", "${n}");
            val diff = secretNumber.validate(n)
            var message = when {
                diff < 0 -> {
                    "猜大一點"
                }
                diff > 0 -> {
                    "猜小一點"
                }
                else -> {
                    "YES"
                }
            }
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(context)
                .setTitle("Message")
                .setMessage(message)
                .setPositiveButton("ok", null)
                .show()
        }
    }
}
