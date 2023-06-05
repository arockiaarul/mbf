package com.youcloud.mbf.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public static Date getDate(String dateStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return formatter.parse(dateStr);// LocalDate.parse(dateStr, format);
        } catch (ParseException e) {
            log.error("ParseException :" + e.getMessage(), e);
        }
        return null;
    }

    public static Date getDateInDDMMYYYY(String dateStr) {
        return getDate(dateStr, "DDMMYYYY");
    }
}
