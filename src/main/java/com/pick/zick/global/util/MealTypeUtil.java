package com.pick.zick.global.util;

import com.pick.zick.domain.student.entity.MealType;

import java.time.LocalTime;

public class MealTypeUtil {
    public static MealType getCurrentMealType(){
        LocalTime now = LocalTime.now();

        if (now.isBefore(LocalTime.of(7,50))) {
            return MealType.BREAKFAST;
        }
        else if (now.isBefore(LocalTime.of(11,50))) {
            return MealType.LUNCH;
        }
        else {
            return MealType.DINNER;
        }
    }
}
