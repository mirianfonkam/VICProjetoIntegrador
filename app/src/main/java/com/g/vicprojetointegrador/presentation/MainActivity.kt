package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.g.vicprojetointegrador.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }
}