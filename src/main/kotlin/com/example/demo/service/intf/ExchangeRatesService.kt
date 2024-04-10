package com.example.demo.service.intf

import com.example.demo.models.dto.LatestCurrencyDTO


interface ExchangeRatesService {

    fun getLatestCurrency(): LatestCurrencyDTO?
    fun getAllCurrencies(): Map<String,String>?
    fun getCurrencyHistory(date:String,currency:String?) : LatestCurrencyDTO?

}