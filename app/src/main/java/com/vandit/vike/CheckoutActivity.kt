package com.vandit.vike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vandit.vike.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bikeName = intent.getStringExtra("bikeName").toString()
        val bikePrice = intent.getStringExtra("bikePrice").toString()

        binding.apply {
            nameTV.text = bikeName
            priceTV.text = "Rs. ${bikePrice}/hr"
            val temp = durationTV.text.toString()
            val total = bikePrice.toInt() * temp.toInt()
            totalTV.text = "Rs. $total"
        }
    }
}