package com.example.demo

import com.example.demo.service.impl.IndexService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class StartupService(
        val indexService: IndexService,
        val logger: Logger = LoggerFactory.getLogger(StartupService::class.java)
) {
    @EventListener(ApplicationStartedEvent::class)
    fun onServiceStartup(applicationStartedEvent: ApplicationStartedEvent)
    {
        indexService.saveCurrencies()
        indexService.saveLatest()
        logger.info("Database content init")
    }


}