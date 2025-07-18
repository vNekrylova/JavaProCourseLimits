package org.example.dto;

import java.math.BigDecimal;

public record LimitDto(Long clientId, BigDecimal dailyLimit) {
}
