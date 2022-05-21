package com.minionz.backend.calendar.controller;

import com.minionz.backend.calendar.controller.dto.CalendarInfoResponseDto;
import com.minionz.backend.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CalendarController {
    private static final String CALENDAR_INFO_SEARCH_SUCCESS="calendar info search success!";
    private final CalendarService calendarService;

    @GetMapping("/calendar/{id}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<CalendarInfoResponseDto> calendarinfo(@PathVariable Long id,@PathVariable String date){

        log.info(CALENDAR_INFO_SEARCH_SUCCESS);
        return calendarService.calendar_info(id, date);

    }
}
