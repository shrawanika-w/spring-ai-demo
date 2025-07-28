package com.beautifulsoftware.ai.springdemo.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Function tools
 * {@link com.beautifulsoftware.ai.springdemo.controller.ChatToolsController}
 *
 * @since July 2025
 */
@Component
public class DateTimeTools {

    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {
        String currentTime = LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
        System.out.println("Tool1: get current time called " + currentTime);
        return currentTime;
    }

    @Tool(description = "Set a user alarm for the given time, provided in ISO-8601 format")
    String setAlarm(String time) {
        LocalDateTime alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Tool2: set alarm called " + alarmTime);
        return alarmTime.toString();
    }
}