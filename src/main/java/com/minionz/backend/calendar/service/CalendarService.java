package com.minionz.backend.calendar.service;

import com.minionz.backend.calendar.controller.dto.CalendarInfoRequestDto;
import com.minionz.backend.calendar.controller.dto.CalendarInfoResponseDto;
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

    public List<CalendarInfoResponseDto> calendar_info(Long id, CalendarInfoRequestDto calendarInfoRequestDto){
        CalendarInfoResponseDto morning = null;
        CalendarInfoResponseDto lunch = null;
        CalendarInfoResponseDto dinner = null;
        List<Calendar> calendar = calendarRepository.findAllByUseridAndFoodDate(id, calendarInfoRequestDto.getFoodDate());

        for(Calendar c : calendar){
            if(c.getFoodTime()== "아침")
                morning = new CalendarInfoResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
            if(c.getFoodTime()== "점심")
                lunch = new CalendarInfoResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
            if(c.getFoodTime()== "저녁")
                dinner = new CalendarInfoResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
        }

        List<CalendarInfoResponseDto> result = Arrays.asList(morning, lunch, dinner);
        return result;
    }
}
