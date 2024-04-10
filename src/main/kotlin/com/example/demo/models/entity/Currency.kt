package com.example.demo.models.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Currency(
        @Id
        @GeneratedValue
        var id:Long?,
        var isoCode:String,
        var name:String,
)





