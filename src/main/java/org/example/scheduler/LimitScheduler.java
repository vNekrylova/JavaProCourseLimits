package org.example.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.LimitService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class LimitScheduler {

    private final LimitService limitService;

    @Scheduled(cron = "${scheduler.reset-daily-limits-cron}")
    public void resetDailyLimits() {
        limitService.resetDailyLimits();
        log.info("Сброс лимитов выполнен в {}", LocalDateTime.now());
    }
}
