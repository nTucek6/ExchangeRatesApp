package com.example.demo.models.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class TimeSeries(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long?,
        var date:String,
        var base:String,
        var isoCode:String,
        var currencyValue:Double,
)
