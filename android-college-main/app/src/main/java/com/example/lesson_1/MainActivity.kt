package com.example.lesson_1

import android.app.Activity
import android.app.ComponentCaller
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var FLAG = "flag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        findViewById<Button>(R.id.secondButton).setOnClickListener{
            goToSecondActivity()
        }

        findViewById<Button>(R.id.thirdButton).setOnClickListener{
            goToThirdActivity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val textView : TextView = findViewById(R.id.textView)
        changeFlag(sharedPref.getBoolean(FLAG, false), textView)

        findViewById<Button>(R.id.bClick).setOnClickListener{
            onClick(sharedPref)
        }
    }

    private var flag : Boolean = false

    fun goToSecondActivity(){
        val intent = Intent(this, NewActivity :: class.java)
        intent.putExtra("Value", flag)
        startActivityForResult(intent, 1)
    }

    fun goToThirdActivity(){
        val intent = Intent(this, Activity3 :: class.java)
        intent.putExtra("Value", flag)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 || requestCode == Activity.RESULT_OK){
            var result = data?.getStringExtra("Value")
            Toast.makeText(this, "Отримано: $result", Toast.LENGTH_SHORT).show()
        }
    }

    fun changeFlag(flag : Boolean, textView : TextView){
        if (flag) {
            textView.text = getString(R.string.on)
        } else {
            textView.text = getString(R.string.off)
        }
    }


    fun onClick(sharedPref: SharedPreferences){

        val textView : TextView = findViewById(R.id.textView)

        flag = !flag
        changeFlag(flag, textView)

        sharedPref.edit().putBoolean(FLAG, flag).apply()
    }

}