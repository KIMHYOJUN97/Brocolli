package com.minionz.backend.foodlist.service;

import com.minionz.backend.calendar.controller.dto.SumFoodRequestDto;
import com.minionz.backend.calendar.domain.Calendar;
import com.minionz.backend.calendar.domain.CalendarRepository;
import com.minionz.backend.common.domain.Message;
import com.minionz.backend.common.exception.NotFoundException;
import com.minionz.backend.foodlist.controller.dto.*;
import com.minionz.backend.foodlist.domain.Food;
import com.minionz.backend.foodlist.domain.FoodList;
import com.minionz.backend.foodlist.domain.FoodListRepository;
import com.minionz.backend.foodlist.domain.FoodRepository;
import com.minionz.backend.user.domain.User;
import com.minionz.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodService {
    private static final String Food_Find_Fail_MESSAGE = "음식 찾기 실패";
    private static final String NO_USER_ERROR_MESSAGE = "해당 유저가 존재하지 않습니다.";
    private static final String Food_Save_SUCCESS_MESSAGE = "음식 저장 성공";
    private static final String no_calendar_message = "해당 캘린더가 존재하지 않습니다.";
    private final FoodRepository foodRepository;
    private final FoodListRepository foodListRepository;
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    public FoodTotalResponseDto myfind(FoodRequestDto foodRequestDto) {
        String[] food_name = {foodRequestDto.getFood_Name1(), foodRequestDto.getFood_Name2(), foodRequestDto.getFood_Name3(), foodRequestDto.getFood_Name4(), foodRequestDto.getFood_Name5()};
        int[] food_person ={foodRequestDto.getFoodperson1(),foodRequestDto.getFoodperson2(),foodRequestDto.getFoodperson3(),foodRequestDto.getFoodperson4(),foodRequestDto.getFoodperson5()};
        double foodTan = 0;
        double foodDan = 0;
        double foodJi = 0;
        double foodKcal=0;
        User user = userRepository.findById(foodRequestDto.getUserid())
                .orElseThrow(() -> new NotFoundException(NO_USER_ERROR_MESSAGE));;
        FoodList foodList = null;
        for (int i = 0; i < 5; i++) {
            if (Objects.equals(food_name[i], "")){
                continue;
            }
            foodList =foodListRepository.findFoodListByFoodListName(food_name[i])
                    .orElseThrow(() -> new NotFoundException(Food_Find_Fail_MESSAGE));
            foodTan += foodList.getFoodTan()*food_person[i];
            foodDan += foodList.getFoodDan()*food_person[i];
            foodJi += foodList.getFoodJi()*food_person[i];
            foodKcal += foodList.getFoodKcal()*food_person[i];
        }
        FoodTotalResponseDto result = new FoodTotalResponseDto(foodKcal,foodTan,foodDan,foodJi, foodRequestDto.getFoodtime());
        Calendar dup_check = calendarRepository.findByUserIdAndFoodTime(foodRequestDto.getUserid(),foodRequestDto.getFoodtime())
                .orElseThrow(() -> new NotFoundException(no_calendar_message));
        if(dup_check != null){//중복이 존재.
            foodKcal += dup_check.getSumFoodKcal();
            foodTan += dup_check.getSumFoodTan();
            foodDan += dup_check.getSumFoodDan();
            foodJi += dup_check.getSumFoodJi();

            dup_check.setSumFoodKcal(foodKcal);
            dup_check.setSumFoodTan(foodTan);
            dup_check.setSumFoodDan(foodDan);
            dup_check.setSumFoodJi(foodJi);
            calendarRepository.save(dup_check);
        }
        else {
            SumFoodRequestDto sumFoodRequestDto = new SumFoodRequestDto(foodKcal, foodTan, foodDan, foodJi, foodRequestDto.getFoodtime(), foodRequestDto.getFooddate());
            Calendar calendar = sumFoodRequestDto.toCalendar(user);
            calendarRepository.save(calendar);
        }
        return result;
    }

//    public List<FoodResponseDto> show(Long id) {
//        Food food = foodRepository.findById(id)
//                .orElseThrow(()-> new NotFoundException(Food_Find_Fail_MESSAGE));
//        List<FoodList> foodLists = food.getFoodLists();
//        return foodLists.stream()
//                .map(foodList -> new FoodResponseDto(foodList))
//                .collect(Collectors.toList());
//    }
}
