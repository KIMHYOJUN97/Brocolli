package com.minionz.backend.calendar.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalendarInfoResponseDto {
    private double sumFoodKcal;
    private double sumFoodTan;
    private double sumFoodDan;
    private double sumFoodJi;
    private String foodTime;

    public CalendarInfoResponseDto(double sumFoodKcal, double sumFoodTan, double sumFoodDan, double sumFoodJi, String foodTime) {
        this.sumFoodKcal = sumFoodKcal;
        this.sumFoodTan = sumFoodTan;
        this.sumFoodDan = sumFoodDan;
        this.sumFoodJi = sumFoodJi;
        this.foodTime = foodTime;
    }
}
