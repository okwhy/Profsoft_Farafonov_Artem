package com.farf.intent_nav

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.farf.intent_nav.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_MESSAGE = "MESSAGE"
        fun newIntent(context: Context, message: String): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
        }
    }

    private var toast: Toast? = null
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        binding.buttonNotify.setOnClickListener {
            toast?.cancel()
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
                show()
            }
        }
    }
}