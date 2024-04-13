package com.example.demo.controller

import com.example.demo.models.dto.VueMultiSelectDTO
import com.example.demo.models.entity.Latest
import com.example.demo.models.entity.TimeSeries
import com.example.demo.service.intf.IndexService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["http://localhost:5173"])
@RestController
class IndexController(val indexService: IndexService,
                      val logger: Logger = LoggerFactory.getLogger(IndexController::class.java)) {

   /* @GetMapping("/saveLatest")
    fun saveLatest() {
        indexService.saveLatest()
    }

    @GetMapping("/getLatest")
    fun getLatest(): List<Latest> {
        return indexService.getLatest()
    }
    */
    @GetMapping("/getCurrency")
    fun getCurrency(): List<VueMultiSelectDTO> {
        logger.info("getCurrency Called")
        return indexService.getCurrency()
    }
    @GetMapping("/convertCurrency")
    fun convertCurrency(
            @RequestParam(required = true) value: Double,
            @RequestParam(required = true) from: String,
            @RequestParam(required = true) to: String,
    ): Double {
        logger.info("convertCurrency Called")
        return indexService.convertCurrency(value, from, to)
    }
    @GetMapping("/getCurrencyHistory")
    fun getCurrencyHistory(
            @RequestParam(required = true) date: String,
            @RequestParam(required = false) currency: String,
    ): MutableList<Latest> {
        logger.info("getCurrencyHistory Called")
        return indexService.getCurrencyHistory(date, currency)
    }
    @GetMapping("/getTimeSeries")
    fun getTimeSeries(
            @RequestParam(required = true) dateFrom: String,
            @RequestParam(required = true) dateTo: String,
            @RequestParam(required = true) currency: String,
    ): MutableList<TimeSeries>? {
        logger.info("getTimeSeries Called")
        return indexService.getTimeSeries(dateFrom,dateTo, currency)
    }
}