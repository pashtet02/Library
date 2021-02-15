package com.library.name.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Utils {
    public static double calculateUserFine(List<Date> list){
        Date timeStamp = Date.valueOf(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
        return 0.0;
    }
}
