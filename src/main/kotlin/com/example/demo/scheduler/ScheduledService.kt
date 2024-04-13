package com.example.demo.scheduler

import com.example.demo.StartupService
import com.example.demo.service.impl.IndexService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ScheduledService(val indexService: IndexService,
                       val logger: Logger = LoggerFactory.getLogger(StartupService::class.java)
) {

    //@Scheduled(fixedRate = 24*60*60*1000)
    @Scheduled(cron = "0 0 0 * * *")
    fun saveLatest() {
        indexService.saveLatest()
        logger.info("Save latest currency at midnight")
    }

}