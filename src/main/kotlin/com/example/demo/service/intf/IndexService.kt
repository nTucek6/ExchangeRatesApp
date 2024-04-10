package com.example.demo.service.intf

import com.example.demo.models.dto.VueMultiSelectDTO
import com.example.demo.models.entity.Currency
import com.example.demo.models.entity.Latest
import com.example.demo.models.entity.TimeSeries

interface IndexService {

    fun saveLatest()
    fun saveCurrencies()
    fun getLatest(): List<Latest>
    fun getCurrency(): List<VueMultiSelectDTO>
    fun convertCurrency(value: Double, from: String, to: String): Double
    fun getCurrencyHistory(date: String, currency: String?): MutableList<Latest>
    fun getTimeSeries(dateFrom:String, dateTo:String,currency: String): MutableList<TimeSeries>

}