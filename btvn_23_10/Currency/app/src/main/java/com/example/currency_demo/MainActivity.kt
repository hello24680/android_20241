package com.example.currency_demo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var sourceAmountEditText: EditText
    private lateinit var sourceCurrencySpinner: Spinner
    private lateinit var targetAmountEditText: EditText
    private lateinit var targetCurrencySpinner: Spinner

    private val currencyRates = mapOf(
        "USD" to 1.0, // Tỷ giá cho USD
        "EUR" to 0.85, // Tỷ giá cho EUR
        "VND" to 23000.0, // Tỷ giá cho VND
        "JPY" to 168.0, // Tỷ giá cho JPY
        "CNY" to 6.5    // Tỷ giá cho CNY
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sourceAmountEditText = findViewById(R.id.sourceAmountEditText)
        sourceCurrencySpinner = findViewById(R.id.sourceCurrencySpinner)
        targetAmountEditText = findViewById(R.id.targetAmountEditText)
        targetCurrencySpinner = findViewById(R.id.targetCurrencySpinner)

        val currencies = currencyRates.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sourceCurrencySpinner.adapter = adapter
        targetCurrencySpinner.adapter = adapter

        setListeners()
    }

    private fun setListeners() {
        sourceAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sourceCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        targetCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun convertCurrency() {
        val sourceAmountText = sourceAmountEditText.text.toString()
        if (sourceAmountText.isNotEmpty()) {
            val sourceAmount = sourceAmountText.toDouble()
            val sourceCurrency = sourceCurrencySpinner.selectedItem.toString()
            val targetCurrency = targetCurrencySpinner.selectedItem.toString()

            // Tính toán số tiền sau khi chuyển đổi
            val sourceRate = currencyRates[sourceCurrency] ?: 1.0
            val targetRate = currencyRates[targetCurrency] ?: 1.0
            val targetAmount = (sourceAmount / sourceRate) * targetRate

            targetAmountEditText.setText(String.format("%.2f", targetAmount))
        } else {
            targetAmountEditText.setText("")
        }
    }
}
