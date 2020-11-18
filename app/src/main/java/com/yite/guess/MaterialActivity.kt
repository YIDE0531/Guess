package com.yite.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    var secretNumber = SecretNumber()
    var context = this
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        fab.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    secretNumber.reset()
                    tv_number.text = secretNumber.count.toString()
                    edt_number.setText("")
                    tv_secret.text = "secretNumber is " + secretNumber.secret.toString()
                }
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }
        secretNumber.printNumber()
        tv_number.text = secretNumber.count.toString()
        tv_secret.text = "secretNumber is " + secretNumber.secret.toString()
    }

    fun check(view: View){
        if(secretNumber.checkNumber(edt_number.text.toString())) {
            var n = edt_number.text.toString().toInt()
            Log.e("number", "${n}");
            if(n>10||n<1){
                Toast.makeText(this, "請輸入1~10的整數", Toast.LENGTH_SHORT).show()
                return
            }
            val diff = secretNumber.validate(n)
            var message = when {
                diff < 0 -> {
                    getString(R.string.yes_you_got_it)
                }
                diff > 0 -> {
                    getString(R.string.smaller)
                }
                diff == 0 && secretNumber.count<3 -> {
                    getString(R.string.bingo) + " ${secretNumber.secret}"
                }
                else -> {
                    getString(R.string.yes)
                }
            }

            tv_number.text = secretNumber.count.toString()
            AlertDialog.Builder(context)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show()
        }else{
            Toast.makeText(this, "請輸入整數", Toast.LENGTH_SHORT).show()
        }
    }
}