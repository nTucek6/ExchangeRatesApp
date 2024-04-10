package com.example.demo.scheduler

import com.example.demo.service.impl.IndexService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduledService(val indexService: IndexService
) {

    @Scheduled(fixedRate = 60*60*1000)
    fun saveLatest() {
        indexService.saveLatest()
    }

}