package cn.com.djin.ssm.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 日期字符串转换的工具类
* */
public class DateUtil {
     private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //字符串 转成 日期
    public static Date stringToDate(String str){
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    //日期 转成 字符串
    public static String dateToString(Date date){
        return sdf.format(date);
    }
}
