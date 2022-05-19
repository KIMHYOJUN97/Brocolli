package com.minionz.backend.calendar.domain;

import com.minionz.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Long> {

    @Query("select c from Calendar c where c.user = :user_id and c.foodDate = :food_date")
    List<Calendar> findAllByUserIdAndFoodDate(@Param("user_id") Long user_id,@Param("food_date") String food_date);
}
