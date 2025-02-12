package com.example.lesson_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val message = intent.getBooleanExtra("Value", false)

        val textView : TextView = findViewById(R.id.textView2)
        textView.text = message.toString()
    }

    fun onClick(view : View){
        var resultIntent = Intent()
        resultIntent.putExtra("Value", "Ви повернулися з NewActivity")
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

}