package com.example.demo.models.dto


data class LatestCurrencyDTO(
        val base : String,
        val rates : Map<String,Double>,
)

