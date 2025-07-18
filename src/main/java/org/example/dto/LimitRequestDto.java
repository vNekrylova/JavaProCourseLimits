package org.example.dto;

import java.math.BigDecimal;

public record LimitRequestDto(Long clientId, BigDecimal amount) {
}
