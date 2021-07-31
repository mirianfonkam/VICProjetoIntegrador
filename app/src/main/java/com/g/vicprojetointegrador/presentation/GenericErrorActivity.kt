package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.presentation.viewmodel.MainViewModel

class GenericErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_error)

        //val tvTryAgain = findViewById<TextView>(R.id.tvTryAgain)
        //val btnClose = findViewById<ImageButton>(R.id.btnClose)
    }

    //Function attached to tvTryAgain and btnClose in the layout
    fun buttonClicked() {
        val intent = Intent(this,MainViewModel::class.java)
        startActivity(intent)
    }

}