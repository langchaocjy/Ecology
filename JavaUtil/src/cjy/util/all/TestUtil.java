package cjy.util.all;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestUtil {
    public static void main(String[] args){
    	TimeUtil timeUtil = new TimeUtil();
    	String a = timeUtil.timeAdd("2018-12-31", 30);
    	System.out.println(a);
    }
    
}
