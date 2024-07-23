package com.farf.intent_nav


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farf.intent_nav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonNav.setOnClickListener {
            val intent = SecondActivity.newIntent(this, "Some TEXTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")
            startActivity(intent)
        }
    }
}