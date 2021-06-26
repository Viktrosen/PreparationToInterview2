package com.example.preparationtointerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.preparationtointerview2.R

class MainActivity : AppCompatActivity() {

    companion object{
        var id:String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}