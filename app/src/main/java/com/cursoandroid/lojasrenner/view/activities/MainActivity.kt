package com.cursoandroid.lojasrenner.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cursoandroid.lojasrenner.R
import com.cursoandroid.lojasrenner.view.fragments.ProdutoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1000)
        setTheme(R.style.Theme_LojasRenner)

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ProdutoFragment())
                .commitNow()
        }
    }
}