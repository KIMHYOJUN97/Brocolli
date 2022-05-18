package com.minionz.backend.calendar.service;

import com.minionz.backend.calendar.controller.dto.SumFoodRequestDto;
import com.minionz.backend.calendar.controller.dto.SumFoodResponseDto;
import com.minionz.backend.calendar.domain.Calendar;
import com.minionz.backend.calendar.domain.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public List<SumFoodResponseDto> calendar_info(Long id, SumFoodRequestDto sumFoodRequestDto){
        SumFoodResponseDto morning = null;
        SumFoodResponseDto lunch = null;
        SumFoodResponseDto dinner = null;
        List<Calendar> calendar = calendarRepository.findByUseridAndFoodDate(id, sumFoodRequestDto.getFoodDate());

        for(Calendar c : calendar){
            if(c.getFoodTime()== "아침")
                morning = new SumFoodResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
            if(c.getFoodTime()== "점심")
                lunch = new SumFoodResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
            if(c.getFoodTime()== "저녁")
                dinner = new SumFoodResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
        }

        List<SumFoodResponseDto> result = Arrays.asList(morning, lunch, dinner);
        return result;
    }
}
