package com.example.demo.service.impl

import com.example.demo.models.dto.VueMultiSelectDTO
import com.example.demo.models.entity.Currency
import com.example.demo.models.entity.Latest
import com.example.demo.models.entity.TimeSeries
import com.example.demo.repository.CurrencyRepository
import com.example.demo.repository.LatestRepository
import com.example.demo.service.intf.ExchangeRatesService
import com.example.demo.service.intf.IndexService
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

@Service
class IndexService(
        var currencyRepository: CurrencyRepository,
        var latestRepository: LatestRepository,
        var exchangeRatesService: ExchangeRatesService) : IndexService {

    override fun saveLatest() {
        if(latestRepository.count() > 0)
            latestRepository.deleteAll()

        val response = exchangeRatesService.getLatestCurrency()
        response?.rates?.forEach { r ->
            latestRepository.save(Latest(id = null, base = response.base, isoCode = r.key, currencyValue = r.value))
        }
    }

    override fun saveCurrencies() {
        val response = exchangeRatesService.getAllCurrencies()
        if (response != null) {
            response.forEach { c ->
                currencyRepository.save(Currency(id = null, isoCode = c.key, name = c.value))
            }
        }
    }

    override fun getLatest(): List<Latest> {
        return latestRepository.findAll()
    }

    override fun getCurrency(): List<VueMultiSelectDTO> {
        return currencyRepository.findAll().map { original -> VueMultiSelectDTO(value = original.isoCode, label = original.name) }
    }

    override fun convertCurrency(value: Double, from: String, to: String): Double {
        val currencyFrom = latestRepository.findByIsoCode(from).currencyValue
        val currencyTo = latestRepository.findByIsoCode(to).currencyValue
        val result = value * (1 / currencyFrom) * currencyTo
        return result
    }

    override fun getCurrencyHistory(date: String, currency: String?): MutableList<Latest> {
        val response = exchangeRatesService.getCurrencyHistory(date, currency)

        var history: MutableList<Latest> = ArrayList()

        if (response != null) {
            response.rates.forEach { r ->
                history.add(Latest(id = null, base = response.base, isoCode = r.key, currencyValue = r.value))
            }
        }
        return history
    }

    override fun getTimeSeries(dateFrom: String, dateTo: String, currency: String): MutableList<TimeSeries> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var history: MutableList<TimeSeries> = ArrayList()

        val dateFromF: Date = dateFormat.parse(dateFrom)
        val dateToF: Date = dateFormat.parse(dateTo)

        var dynamicDateFrom = dateFromF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        var dynamicDateTo = dateToF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        while (dynamicDateFrom <= dynamicDateTo) {
            var response = exchangeRatesService.getCurrencyHistory(dynamicDateFrom.toString(), currency)
            if (response != null) {
                response.rates.forEach { r ->
                    history.add(TimeSeries(id = null, base = response.base, isoCode = r.key, date = dynamicDateFrom.toString(),currencyValue = r.value))
                }
            }
            dynamicDateFrom = dynamicDateFrom.plusDays(1)
        }
        return history
    }


}