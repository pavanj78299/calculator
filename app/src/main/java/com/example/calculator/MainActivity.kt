package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var preview: TextView

    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        preview = findViewById(R.id.preview)
    }

    fun onClick(view: View) {

        val button = view as android.widget.Button
        val text = button.text.toString()

        when (text) {

            "AC" -> {
                expression = ""
                preview.text = ""
                result.text = "0"
            }

            "⌫" -> {
                if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                    preview.text = expression

                    if (expression.isEmpty()) {
                        result.text = "0"
                    }
                }
            }

            "=" -> {
                try {

                    val finalExpression = expression
                        .replace("×", "*")
                        .replace("÷", "/")

                    val answer = ExpressionBuilder(finalExpression)
                        .build()
                        .evaluate()

                    preview.text = expression

                    if (answer % 1 == 0.0) {
                        result.text = answer.toInt().toString()
                    } else {
                        result.text = answer.toString()
                    }

                } catch (e: Exception) {
                    result.text = "Error"
                }
            }

            else -> {

                expression += text

                preview.text = expression
                result.text = expression
            }
        }
    }
}