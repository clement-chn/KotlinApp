package com.example.calculatrice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val number1 = findViewById<EditText>(R.id.number1)
        val number2 = findViewById<EditText>(R.id.number2)
        val textResult = findViewById<TextView>(R.id.textResult)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        buttonAdd.setOnClickListener {
            calculate(number1, number2, textResult, "add")
        }

        buttonSubtract.setOnClickListener {
            calculate(number1, number2, textResult, "subtract")
        }

        buttonMultiply.setOnClickListener {
            calculate(number1, number2, textResult, "multiply")
        }

        buttonDivide.setOnClickListener {
            calculate(number1, number2, textResult, "divide")
        }

        val openComposeButton = findViewById<Button>(R.id.button_open_compose)
        openComposeButton.setOnClickListener {
            val intent = Intent(this, ComposeCalculatorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calculate(
        num1Field: EditText, num2Field: EditText, resultView: TextView, operation: String
    ) {
        val num1 = num1Field.text.toString().toIntOrNull()
        val num2 = num2Field.text.toString().toIntOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(
                this, "Veuillez entrer des nombres valides",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val result = when (operation) {
            "add" -> num1 + num2
            "subtract" -> num1 - num2
            "multiply" -> num1 * num2
            "divide" -> if (num2 != 0) num1 / num2 else {
                Toast.makeText(
                    this, "Division par zéro impossible",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            else -> 0
        }

        resultView.text = "Résultat : $result"
    }
}