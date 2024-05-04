package com.example.calculatornurgazy

import android.content.Intent
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
    private lateinit var operationType: String

    private var first = 0.0
    private var second = 0.0
    private var result = 0.0

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.windowInsetsController?.hide(WindowInsets.Type.navigationBars())

        binding.sendBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, BoredActivity::class.java)
            startActivity(intent)
            binding.sendBtn.visibility = View.INVISIBLE
        }
    }

    fun onNumberClick(view: View) {
        with(binding) {
            val text = (view as MaterialButton).text.toString()
            if (text == "AC") {
                mainTv.text = "0"
                first = 0.0; second = 0.0
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

            when (view.id) {
                R.id.plus_btn -> {
                    first =
                        mainTv.text.toString()
                            .toDoubleOrNull()
                            ?: 0.0 // "toIntOrNull()" function converts the string to an integer,  or returns null if the conversion fails. If the conversion fails, we set "first" to a default value (in this case, 0).
                    operationType = "+"
                }

                R.id.minus_btn -> {
                    first = mainTv.text.toString().toDoubleOrNull() ?: 0.0
                    operationType = "-"
                }

                R.id.multiply_btn -> {
                    first = mainTv.text.toString().toDoubleOrNull() ?: 0.0
                    operationType = "*"
                }

                R.id.divide_btn -> {
                    first = mainTv.text.toString().toDoubleOrNull() ?: 0.0
                    operationType = "/"
                }

                R.id.percent_btn -> {
                    first = mainTv.text.toString().toDoubleOrNull() ?: 0.0
                    result = first / 100.0
                    mainTv.text = result.toString()
                }

                R.id.point_btn -> {
                    val currentText = mainTv.text.toString()
                    if (!currentText.contains('.')) {
                        mainTv.append(".")
                    }
                }


                R.id.equal_btn -> {
                    if (mainTv.text == "0") { // В Таком виде работает
                        mainTv.text =
                            "0"               // Это нужно для того чтобы приложение не вылетало когда первым делом заходишь в него нажимаешь равно =
                    } else {
                        second = mainTv.text.toString().toDoubleOrNull() ?: 0.0
                        when (operationType) {
                            "+" -> result = first + second
                            "-" -> result = first - second
                            "*" -> result = first * second
                            "/" -> result = first / second
                        }
                        mainTv.text = result.toString()
                        sendBtn.visibility = View.VISIBLE
                    }
                }
            }
            isOperationClicked = true
        }
    }
}