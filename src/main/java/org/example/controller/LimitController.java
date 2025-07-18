package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.LimitDto;
import org.example.dto.LimitRequestDto;
import org.example.service.LimitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/limits")
@RequiredArgsConstructor
public class LimitController {

    private final LimitService limitService;

    @PatchMapping(value = "/reduce")
    public LimitDto reduceClientLimit(@RequestBody LimitRequestDto limitRequestDto) {
        return limitService.reduceLimit(limitRequestDto);
    }

    @PatchMapping(value = "/restore")//подразумеваем, что при отмене операции возвращают сумму списания
    public LimitDto restoreClientLimit(@RequestBody LimitRequestDto limitRequestDto) {
        return limitService.restoreLimit(limitRequestDto);
    }
}
