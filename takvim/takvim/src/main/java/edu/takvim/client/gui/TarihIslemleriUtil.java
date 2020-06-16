package edu.takvim.client.gui;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

public class TarihIslemleriUtil {
    public static String getStringFormat(Date inputDate) {
        String strFormat = null;
        try {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
            strFormat = dateTimeFormat.format(inputDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strFormat;
    }

    public static Date stringToDate(String date) {
        try {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
            return dateTimeFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
