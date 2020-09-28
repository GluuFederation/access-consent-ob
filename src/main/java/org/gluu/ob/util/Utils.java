package org.gluu.ob.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

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

}
