package com.example.calculatornurgazy

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatornurgazy.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isOperationClicked: Boolean = true

    private var first = 0
    private var second = 0
    private var result = 0

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.windowInsetsController?.hide(WindowInsets.Type.navigationBars())

    }

    fun onNumberClick(view: View) {
        with(binding) {
            val text = (view as MaterialButton).text.toString()
            if (text == "AC") {
                mainTv.text = "0"
                first = 0; second = 0
            } else if (mainTv.text.toString() == "0" || isOperationClicked) {
                mainTv.text = text
            } else {
                mainTv.append(text)
            }
            isOperationClicked = false
        }
    }

    fun onOperationClick(view: View) {
        with(binding) {

            if (view.id == R.id.plus_btn) {
                first = mainTv.text.toString().toIntOrNull()
                    ?: 0   // "toIntOrNull()" function converts the string to an integer,  or returns null if the conversion fails. If the conversion fails, we set first to a default value (in this case, 0).

            } else if (view.id == R.id.equal_btn) {
                second = mainTv.text.toString().toIntOrNull() ?: 0
                result = first + second
                mainTv.text = result.toString()

            } else if (view.id == R.id.minus_btn) {

            }

            isOperationClicked = true
        }
    }
}