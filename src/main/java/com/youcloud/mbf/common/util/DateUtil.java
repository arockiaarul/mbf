package com.youcloud.mbf.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    public static final String DD_MM_YYYY = "ddMMyyyyHHmmss";

    private DateUtil() {
    }

    public static String getCurrentDayInMMDD() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(DateTimeFormatter.ofPattern("MMdd"));
    }

    public static String getCurrentDayInYYMMDD() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(DateTimeFormatter.ofPattern("yyMMdd"));
    }

    public static String getCurrentDayInDDMMYY() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(DateTimeFormatter.ofPattern("ddMMyy"));
    }

    public static String getLocalDateTimeNowStr() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static String getLocalDateTimeNowStr(String format) {
        LocalDateTime today = LocalDateTime.now();
        return today.format(DateTimeFormatter.ofPattern(format));
    }

    public static String convertToPlainString(String date) {
        return date.replaceAll("/", "");
    }

    public static String nowWOMs() {
        return LocalDateTime.now()
                .withNano(0)
                .toString();
    }
}
