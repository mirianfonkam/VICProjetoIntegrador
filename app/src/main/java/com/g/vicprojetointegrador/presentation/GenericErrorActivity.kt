package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.g.vicprojetointegrador.R

class GenericErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_error)

        val tvTryAgain = findViewById<TextView>(R.id.tvTryAgain)
        val btnClose = findViewById<ImageButton>(R.id.btnClose)

        tvTryAgain.setOnClickListener {
            returnHomeActivity()
        }

        btnClose.setOnClickListener {
            returnHomeActivity()
        }

    }

    private fun returnHomeActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}