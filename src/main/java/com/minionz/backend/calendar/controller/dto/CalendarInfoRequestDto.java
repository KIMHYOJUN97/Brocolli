package com.minionz.backend.calendar.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalendarInfoRequestDto {
    private String foodDate;
    private Long userid;

    public CalendarInfoRequestDto(String foodDate, Long userid) {
        this.foodDate = foodDate;
        this.userid = userid;
    }
}
