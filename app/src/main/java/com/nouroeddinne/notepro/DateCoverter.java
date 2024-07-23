package com.nouroeddinne.notepro;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateCoverter {

    @TypeConverter
    public static Date convertDateToLong(Long date) {
        return date==null ? null : new Date(date);
    }

    @TypeConverter
    public static long converLongToDate(Date date) {
        return date==null ? null : date.getTime();
    }

    public static String handleSelectedDate(Date selectedDate) {
        Date currentDate = selectedDate;
        String formattedDate = formatDate(currentDate);
        return formattedDate;
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        return dateFormat.format(date);
    }




}
