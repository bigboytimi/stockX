package com.example.stockx.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String saveLocalDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd h:mm:ss a");
        return date.format(formatter);
    }
}
