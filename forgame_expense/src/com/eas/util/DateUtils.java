package com.eas.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author xs
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static final String PATTERN_DATE="yyyy-MM-dd";
	public static final String PATTERN_DATE_SHORT="yyyyMMdd";
	public static final String PATTERN_DATETIME="yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATETIME_STAMP="yyyyMMddHHmmss";
	public static final String PATTERN_DATETIME_MS="dd-MMM-yyyy HH:mm:ss:SSS";
	
	public static final int dayHasSeconds=86400;
	public static final int dayHasMillisecond=86400000;
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDateFmt(Object str) {
		if (str == null){
			return null;
		}
		try {
			return DateUtils.parseDate(str.toString(), DateUtils.PATTERN_DATE);
		} catch (ParseException e) {
//			e.printStackTrace();
		}
		return null;
	}
	public static Date parseDateTimeFmt(Object str) {
		if (str == null){
			return null;
		}
		try {
			return DateUtils.parseDate(str.toString(), DateUtils.PATTERN_DATETIME);
		} catch (ParseException e) {
//			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
    //这个不对
//	public static double getDistanceOfTwoDate(Date before, Date after) {
//		long beforeTime = before.getTime();
//		long afterTime = after.getTime();
//		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
//	}
	
	
	public static int getCurDateWeek(){
		Date dt = new Date();
		Calendar cal = Calendar.getInstance();
	    //String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	    cal.setTime(dt);

	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (w < 0){
	        w = 0;
	    }
	    return w;
	    //return weekDays[w];
	}
	
   
	/***
	 * 
	 * 获取日期 如果当前日期是周一，周二，周三，周四，周日的话获取当天日期，如果是周五，周六的话获取本周日的日期
	 * 
	 * @return
	 * @return Date
	 * @throws @author
	 *             zc
	 * @create 2016年7月21日 下午10:42:02
	 */
	public static String getDateTime(Object time) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(time.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		String day = getWeekOfDate(date);
		if (day.equals("星期一") || day.equals("星期二") || day.equals("星期三") || day.equals("星期四") || day.equals("星期日")) {
			return df.format(cal.getTime());
		} else if (day.equals("星期五")) {
			cal.add(cal.DATE, 2);
		} else if (day.equals("星期六")) {
			cal.add(cal.DATE, 1);
		}
		return df.format(cal.getTime());
	}
	
	/***
	 * 
	 * 获取当前时间是星期几
	 * 
	 * @description （用一句话描述该方法的适用条件、执行流程、适用方法、注意事项 - 可选）
	 * @param dt
	 * @return
	 * @return String
	 * @throws @author
	 *             zc
	 * @create 2016年7月21日 下午8:45:04
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	
	/**
	 * @Description 当前时间的datetime字符串
	 * @author 张伯文
	 * @date 2016年12月12日下午10:19:33
	 * 
	 * @return
	 */
	public static String getNowDateTimeStr() {
		return DateUtils.formatDate(new Date(), DateUtils.PATTERN_DATETIME);
	}
	/**
	 * @Description 当前时间的date字符串
	 * @author 张伯文
	 * @date 2016年12月12日下午10:19:33
	 * 
	 * @return
	 */
	public static String getNowDateStr() {
		return DateUtils.formatDate(new Date(), DateUtils.PATTERN_DATE);
	}
	/**
	 * @Description 转换日期为一天开始
	 * @author 张伯文
	 * @date 2016年12月12日下午10:19:33
	 */
	public static String toDayStart(Date date) {
		return DateUtils.formatDate(date, DateUtils.PATTERN_DATE)+" 00:00:00";
	}
	/**
	 * @Description 转换日期为一天结束
	 * @author 张伯文
	 * @date 2016年12月12日下午10:19:33
	 */
	public static String toDayEnd(Date date) {
		return DateUtils.formatDate(date, DateUtils.PATTERN_DATE)+" 23:59:59";
	}
	/**
	 * 转换为日期字符串
	 * @author 张伯文
	 * @date 2017年8月29日下午2:45:06
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateStr(Date date) {
		return DateUtils.formatDate(date, DateUtils.PATTERN_DATE);
	}
	
	/**
	 * 转换为日期时间字符串
	 * @author 张伯文
	 * @date 2017年8月29日下午2:45:30
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateTimeStr(Date date) {
		return DateUtils.formatDate(date, DateUtils.PATTERN_DATETIME);
	}
	public static String toShortDateStr(Date date) {
		return DateUtils.formatDate(date, DateUtils.PATTERN_DATE_SHORT);
	}
    

	 /**
	 * 判断是否同一天，忽略时分秒
	 * @author 张伯文
	 * @date 2017年3月29日下午8:57:22
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException 
	 */
	public static boolean isSameDate(final Date date1, final Date date2) throws ParseException {
		return compareDate(date1, date2) == 0;
	}

	public static boolean isBeforeDate(final Date date1, final Date date2) throws ParseException {
		return compareDate(date1, date2) < 0;
	}

	public static boolean isEqualBeforeDate(final Date date1, final Date date2) throws ParseException {
		return compareDate(date1, date2) <= 0;
	}

	public static boolean isEqualAfterDate(final Date date1, final Date date2) throws ParseException {
		return compareDate(date1, date2) >= 0;
	}

	public static boolean isAfterDate(final Date date1, final Date date2) throws ParseException {
		return compareDate(date1, date2) > 0;
	}

	public static boolean isBetweenDate(final Date date, final Date date1, final Date date2) throws ParseException {
		return isEqualAfterDate(date, date1) && isEqualBeforeDate(date, date2);
	}
	
    /**
     * 比较相差日期，只看日期，忽略时分秒，返回date1-date2相差天数
     * 改进的版本，比DateUtil.daysBetweenDate(stime, etime)快20倍，执行一次约3us
     * @author 张伯文
     * @date 2017年3月7日下午5:30:57
     * 
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int compareDate(final Date date1,final Date date2) throws ParseException {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	long base19000101=sdf.parse("1900-01-01").getTime();
    	return (int)((date1.getTime()-base19000101)/86400000-(date2.getTime()-base19000101)/86400000);
    }
    
    /**
     * 判断date是否在startDate到endDate之间，包含两端日期，只看日期，忽略时分秒
     * @author 张伯文
     * @date 2017年3月7日下午5:30:57
     * 
     * @param sdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static boolean isBetween(final Date date,final Date startDate,final Date endDate) throws ParseException {
    	return compareDate(startDate,date)<=0 && compareDate(date,endDate)<=0;
    }
    
    
    /**
     * 毫秒时间转换为时分秒字符串
     * @author 张伯文
     */
    public static String msToHMSStr(long ms){
    	long h=ms/3600000;
    	long m=(ms%3600000)/60000;
    	long s=(ms%60000)/1000;
    	return h+":"+m+":"+s;
    }
    /**
     * 毫秒时间转换为时分秒字符串
     * @author 张伯文
     */
    public static long HMSToMsStr(String HMS){
    	if(HMS==null || HMS.equals("")){
    		return 0;
    	}
    	String[] bf=HMS.split(":");
    	if(bf.length==3){
        	int h=Integer.valueOf(bf[0]);
        	int m=Integer.valueOf(bf[1]);
        	int s=Integer.valueOf(bf[2]);
        	return h * 3600000 + m * 60000 + s * 1000;
    	}
    	if(bf.length==2){
        	int h=Integer.valueOf(bf[0]);
        	int m=Integer.valueOf(bf[1]);
        	return h * 3600000 + m * 60000 ;
    	}else{
    		return 0;
    	}

    }
    
    
    public static Date addDateDays(final String dateStr, final int amount) throws ParseException {
    	Date date=DateUtils.parseDate(dateStr, DateUtils.PATTERN_DATE);
        return DateUtils.addDays(date, amount);
    }
    public static Date addDateTimeDays(final String dateStr, final int amount) throws ParseException {
    	Date date=DateUtils.parseDate(dateStr, DateUtils.PATTERN_DATETIME);
        return DateUtils.addDays(date, amount);
    }
    public static Date addDateTimeDays(final String dateStr,String format, final int amount) throws ParseException {
    	Date date=DateUtils.parseDate(dateStr, format);
        return DateUtils.addDays(date, amount);
    }
    public static String addDateDaysToString(final String dateStr, final int amount) throws ParseException {
    	Date date=DateUtils.parseDate(dateStr, DateUtils.PATTERN_DATE);
        return formatDate(DateUtils.addDays(date, amount));
    }
    

    /**
     * 获取两个时间之间的天数
     * @param sdate
     * @param bdate
     * @return
     * @throws Exception
     */
    public static int getDaysBetweenDate(String sdate, String bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date pstartDate = sdf.parse(sdate);
        Date pendDate = sdf.parse(bdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(pstartDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(pendDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }
    
    /**
     * 获取某天前一天
     * @param specifiedDay
     * @return
     */
    public static Date getSpecifiedDayBefore(Date date){ 
    	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(date); 
    	int day=c.get(Calendar.DATE); 
    	c.set(Calendar.DATE,day-1); 
    	return c.getTime(); 
    } 
    
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

//		System.out.println(compareDate(parseDate("2012-11-10 23:59:59"), parseDate("2012-11-10 00:00:00")));
//		System.out.println(compareDate(parseDate("2012-11-10 23:59:59"), parseDate("2012-11-11 00:00:00")));
		
		
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
	}
}
