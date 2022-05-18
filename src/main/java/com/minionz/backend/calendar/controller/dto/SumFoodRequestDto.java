package com.minionz.backend.calendar.controller.dto;

import com.minionz.backend.calendar.domain.Calendar;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SumFoodRequestDto {
    private double sumFoodKcal;
    private double sumFoodTan;
    private double sumFoodDan;
    private double sumFoodJi;
    private String foodTime;
    private String foodDate;

    @Builder
    public SumFoodRequestDto(double sumFoodKcal, double sumFoodTan, double sumFoodDan, double sumFoodJi,String foodTime,String foodDate) {
        this.sumFoodKcal = sumFoodKcal;
        this.sumFoodTan = sumFoodTan;
        this.sumFoodDan = sumFoodDan;
        this.sumFoodJi = sumFoodJi;
        this.foodTime =foodTime;
        this.foodDate = foodDate;
    }

    public Calendar toCalendar() {
        return Calendar.builder()
                .sumFoodDan(this.sumFoodDan)
                .sumFoodJi(this.sumFoodJi)
                .sumFoodKcal(this.sumFoodKcal)
                .sumFoodTan(this.sumFoodTan)
                .foodTime(this.foodTime)
                .foodDate(this.foodDate)
                .build();
    }
}
