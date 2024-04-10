package com.example.demo.models.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Latest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long?,
        var base:String,
        var isoCode:String,
        var currencyValue:Double,
)
