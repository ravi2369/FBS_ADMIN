package com.fbs.admin.util;

import com.fbs.admin.exceptions.FBSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtility {

    private static String fbsDateFormat;

    public DateUtility(@Value("${jackson.date-format}") String fbsDateFormat) {
        this.fbsDateFormat = fbsDateFormat;
    }

    public static Object convertToFbsFormat(Object value) {
        try {
            DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(fbsDateFormat);
            LocalDateTime localDateTime = LocalDateTime.parse(value.toString(), simpleDateFormat);
            if (localDateTime.isBefore(LocalDateTime.now())) {
                throw new FBSException("please select future date");
            }
            return localDateTime;
        } catch (Exception e) {
            throw new FBSException("You entered wrong date format " + e.getMessage() + " <--- Please enter valid datetime in this format --> " + fbsDateFormat);
        }
    }

    public static void isFutureOrNot(Object obj) {
        Object date = convertToFbsFormat(obj);
        if (date instanceof LocalDateTime) {
            if (((LocalDateTime) date).isBefore(LocalDateTime.now())) {
                throw new FBSException("please select future date");
            }
        }
    }
}
