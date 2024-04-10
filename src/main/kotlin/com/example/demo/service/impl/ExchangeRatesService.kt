package com.example.demo.service.impl

import com.example.demo.models.dto.LatestCurrencyDTO
import com.example.demo.service.intf.ExchangeRatesService
import jakarta.annotation.Nullable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExchangeRatesService() : ExchangeRatesService {
    private val currencyAPI = "https://openexchangerates.org/api/"
    private val apiKey = "app_id=68d682106410473c8178cedf0fbadd40"
    private val latest = "latest.json?"
    private val currencies  = "currencies.json?"
    private val historical = "historical/"


   override fun getLatestCurrency(): LatestCurrencyDTO? {
     val restTemplate = RestTemplate()
     val response = restTemplate.getForEntity((currencyAPI+latest+apiKey), LatestCurrencyDTO::class.java)

        return if (response.statusCode == HttpStatus.OK) {
            //ResponseEntity.ok(response.body)
            response.body

        } else {
            null
        }
    }

    override fun getAllCurrencies(): Map<String,String>? {
        val restTemplate = RestTemplate()
        val response = restTemplate.getForEntity((currencyAPI+currencies+apiKey), Map::class.java)

        val responseBody: Map<String, String>? = response.body as? Map<String, String>

        return if (response.statusCode == HttpStatus.OK && response.body != null) {
            responseBody
        } else {
            null
        }
    }

    override fun getCurrencyHistory(date:String, currency:String?) : LatestCurrencyDTO?
    {
        //date must be YYYY-MM-DD
        var symbols = "&symbols=$currency"
        if(currency == null)
        {
            symbols = ""
        }
        val restTemplate = RestTemplate()
        val response = restTemplate.getForEntity(("$currencyAPI$historical$date.json?$apiKey$symbols"), LatestCurrencyDTO::class.java)

        return if (response.statusCode == HttpStatus.OK) {
            response.body

        } else {
            null
        }
    }


}