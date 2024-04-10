package com.example.demo.repository

import com.example.demo.models.entity.Latest
import org.springframework.data.repository.CrudRepository

interface LatestRepository : CrudRepository<Latest, Long> {
    fun findByIsoCode(isoCode: String): Latest
    override fun findAll(): List<Latest>
}