package com.example.calculatornurgazy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatornurgazy.databinding.ActivityBoredBinding

class BoredActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoredBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBoredBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            nextBtn.setOnClickListener { finishAffinity() }

            favouriteBtn.tag = R.drawable.ic_heart
            favouriteBtn.setOnClickListener {
                val currentImage = favouriteBtn.tag
                if (currentImage == R.drawable.ic_heart) {
                    favouriteBtn.setImageResource(R.drawable.ic_full_heart)
                    favouriteBtn.tag = R.drawable.ic_full_heart
                } else {
                    favouriteBtn.setImageResource(R.drawable.ic_heart)
                    favouriteBtn.tag = R.drawable.ic_heart
                }
            }
        }
    }
}