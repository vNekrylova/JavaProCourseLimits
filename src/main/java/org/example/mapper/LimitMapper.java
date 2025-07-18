package org.example.mapper;

import org.example.dto.LimitDto;
import org.example.entity.Limit;
import org.springframework.stereotype.Component;

@Component
public class LimitMapper {
    public LimitDto toDto(Limit limit) {
        return new LimitDto(limit.getClientId(), limit.getDailyLimit());
    }
}
