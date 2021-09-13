package com.okt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by obeeey on 2021/3/19
 * 即使再小的帆也能远航
 */
public class DateFormat {


    //日期转换成字符串
    public static String dateToString(Date date, String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format=sdf.format(date);
        return format;
    }

    public static Date stringToDate(String dateStr) throws ParseException {

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            Date date=sdf.parse(dateStr);
            return date;
    }
}
