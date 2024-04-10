package com.example.demo.repository

import com.example.demo.models.entity.Currency
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository : CrudRepository<Currency, Long> {
    override fun findAll(): List<Currency>
}