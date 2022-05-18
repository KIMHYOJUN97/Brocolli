package com.minionz.backend.calendar.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalendarInfoRequestDto {
    private String foodDate;

    public CalendarInfoRequestDto(String foodDate) {
        this.foodDate = foodDate;
    }
}
