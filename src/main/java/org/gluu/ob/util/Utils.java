package org.gluu.ob.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Utils {

    public static String formatDate(Date dateTime, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(dateTime);
        } catch (Exception e) {
            log.error("Error formatting datetime: {}, format: {}", dateTime, format);
            return null;
        }
    }

    public static Date parseDate(String value, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(value);
        } catch (Exception e) {
            log.error("Error formatting datetime: {}, format: {}", value, format);
            return null;
        }
    }

}
