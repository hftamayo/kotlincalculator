package com.halftamayo.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    //variables to hold operations and symbols
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        val button0: Button = findViewById(R.id.btnZero)
        val button1: Button = findViewById(R.id.btnOne)
        val button2: Button = findViewById(R.id.btnTwo)
        val button3: Button = findViewById(R.id.btnThree)
        val button4: Button = findViewById(R.id.btnFour)
        val button5: Button = findViewById(R.id.btnFive)
        val button6: Button = findViewById(R.id.btnSix)
        val button7: Button = findViewById(R.id.btnSeven)
        val button8: Button = findViewById(R.id.btnEight)
        val button9: Button = findViewById(R.id.btnNine)
        val buttonDot: Button = findViewById(R.id.btnPoint)
        val buttonPer: Button = findViewById(R.id.btnPercentage)

        //operation buttons
        val buttonEquals = findViewById<Button>(R.id.btnEquals)
        val buttonDivide = findViewById<Button>(R.id.btnDiv)
        val buttonMultiply = findViewById<Button>(R.id.btnMult)
        val buttonMinus = findViewById<Button>(R.id.btnMin)
        val buttonPlus = findViewById<Button>(R.id.btnSum)

        val listener = View.OnClickListener { v ->
        val b = v as Button
        newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)
        buttonPer.setOnClickListener(listener)

        buttonEquals.setOnClickListener(listener)
        buttonDivide.setOnClickListener(listener)
        buttonMultiply.setOnClickListener(listener)
        buttonMinus.setOnClickListener(listener)
        buttonPlus.setOnClickListener(listener)

        val optListener = View.OnClickListener{ v ->
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if (value.isNotEmpty()){
                performOperation(value, op)
            }
            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonEquals.setOnClickListener(optListener)
        buttonDivide.setOnClickListener(optListener)
        buttonMultiply.setOnClickListener(optListener)
        buttonMinus.setOnClickListener(optListener)
        buttonPlus.setOnClickListener(optListener)
    }

    private fun performOperation(value: String, operation: String ){
        if(operand1 == null){
            operand1 = value.toDouble()
        } else {
            operand2 = value.toDouble()

            if(pendingOperation == "="){
                pendingOperation = operation
            }
            when(pendingOperation){
                "=" -> operand1 = operand2
                "/" -> if(operand2 == 0.0){
                            operand1 = Double.NaN //handle attempt to divide by zero
                        } else {
                            operand1 = operand1!! / operand2
                        }
                "*" -> operand1 = operand1!! * operand2
                "-" -> operand1 = operand1!! - operand2
                "+" -> operand1 = operand1!! + operand2
            }
        }
        result.setText(operand1.toString())
        newNumber.setText("")
    }
}