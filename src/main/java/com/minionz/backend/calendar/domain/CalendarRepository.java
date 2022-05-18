package com.minionz.backend.calendar.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Long> {

    List<Calendar> findAllByUseridAndFoodDate(Long id,String fooddate);
}
