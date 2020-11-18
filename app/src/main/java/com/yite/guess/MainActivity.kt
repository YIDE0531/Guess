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
    val TAG = MainActivity::class.java.simpleName

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
                    getString(R.string.yes_you_got_it)
                }
                diff > 0 -> {
                    getString(R.string.smaller)
                }
                else -> {
                    getString(R.string.yes)
                }
            }
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(context)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show()
        }
    }
}
