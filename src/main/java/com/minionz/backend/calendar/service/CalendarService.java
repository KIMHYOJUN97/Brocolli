package com.minionz.backend.calendar.service;

import com.minionz.backend.calendar.controller.dto.CalendarInfoResponseDto;
import com.minionz.backend.calendar.domain.Calendar;
import com.minionz.backend.calendar.domain.CalendarRepository;
import com.minionz.backend.user.domain.UserRepository;
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
    private final UserRepository userRepository;
    private static final String usernotfoundmessage = "user isn`t haven";

    public List<CalendarInfoResponseDto> calendar_info(Long id, String date){
        CalendarInfoResponseDto morning = null;
        CalendarInfoResponseDto lunch = null;
        CalendarInfoResponseDto dinner = null;
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(usernotfoundmessage));;
        List<Calendar> calendar = calendarRepository.findAllByUserIdAndFoodDate(id, date);

        for(Calendar c : calendar){
            if(c.getFoodTime().equals("아침") )
                morning = new CalendarInfoResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
            if(c.getFoodTime().equals("점심") )
                lunch = new CalendarInfoResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
            if(c.getFoodTime().equals("저녁"))
                dinner = new CalendarInfoResponseDto(c.getSumFoodKcal(), c.getSumFoodTan(), c.getSumFoodDan(), c.getSumFoodJi(), c.getFoodTime());
        }

        List<CalendarInfoResponseDto> result = Arrays.asList(morning, lunch, dinner);
        return result;
    }
}
