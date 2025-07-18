package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.config.properties.LimitProperties;
import org.example.dto.LimitDto;
import org.example.dto.LimitRequestDto;
import org.example.entity.Limit;
import org.example.exceptions.LimitException;
import org.example.mapper.LimitMapper;
import org.example.repository.LimitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;
    private final LimitMapper limitMapper;
    private final LimitProperties limitProperties;

    @Transactional
    public LimitDto reduceLimit(LimitRequestDto limitRequestDto) {
        Long clientId = limitRequestDto.clientId();
        Limit limit = limitRepository.findByClientId(clientId).orElseGet(()->createLimit(clientId));

        if (limit.getDailyLimit().compareTo(limitRequestDto.amount()) < 0) {
            throw new LimitException("Лимит клиента превышен");
        }

        limit.setDailyLimit(limit.getDailyLimit().subtract(limitRequestDto.amount()));
        limitRepository.save(limit);

        return limitMapper.toDto(limit);
    }

    @Transactional
    public LimitDto restoreLimit(LimitRequestDto limitRequestDto) {
        Long clientId = limitRequestDto.clientId();
        Optional<Limit> optionalLimit = limitRepository.findByClientId(clientId);

        Limit limit;
        if (optionalLimit.isPresent()) {
            limit = optionalLimit.get();

            BigDecimal newDailyLimit = limit.getDailyLimit().add(limitRequestDto.amount());
            BigDecimal defaultLimit = limitProperties.getDefaultValue();
            limit.setDailyLimit(newDailyLimit.compareTo(defaultLimit) > 0 ? defaultLimit : newDailyLimit);

            limitRepository.save(limit);
        } else {
            limit = createLimit(clientId);
        }

        return limitMapper.toDto(limit);
    }

    @Transactional
    public void resetDailyLimits() {
        limitRepository.updateDailyLimit(limitProperties.getDefaultValue());
    }

    private Limit createLimit(Long clientId) {
        Limit limit = new Limit();
        limit.setClientId(clientId);
        limit.setDailyLimit(limitProperties.getDefaultValue());
        limitRepository.save(limit);
        return limit;
    }
}
