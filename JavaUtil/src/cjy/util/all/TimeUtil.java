package cjy.util.all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Author:泛微陈嘉源
 * */
public class TimeUtil
{
    public static void main(String[] args){
    	   Calendar localCalendar = getCalendar("2018-12-01");
    	   int j = localCalendar.getActualMaximum(5);
    	   System.out.println(j);
    	   int aa = getWeeksOfMonth("2018",12);
    	   System.out.println(aa);
    }
    /**
      * paramString:19位或10位数具体时间
      * paramInt:单位为秒
      * 1：对年份操作，2：对月份操作，3：对星期操作，5：对日期操作，11：对小时操作，12：对分钟操作，13：对秒操作，14：对毫秒操作
      * */
  public static String timeAdd(String paramString, int paramInt)
  {
    Calendar localCalendar = getCalendar(paramString);
    if (localCalendar == null) {
      return null;
    }
    localCalendar.add(13, paramInt);
    
    return getTimeString(localCalendar);
  }
  
  public static long timeInterval(String paramString1, String paramString2)
  {
    Calendar localCalendar1 = getCalendar(paramString1);
    Calendar localCalendar2 = getCalendar(paramString2);
    if ((localCalendar1 == null) || (localCalendar2 == null)) {
      return 0L;
    }
    return (localCalendar2.getTime().getTime() - localCalendar1.getTime().getTime()) / 1000L;
  }
  
  public static String dateAdd(String paramString, int paramInt)
  {
    Calendar localCalendar = getCalendar(paramString);
    if (localCalendar == null) {
      return null;
    }
    localCalendar.add(5, paramInt);
    
    return getDateString(localCalendar);
  }
  
  public static int dateInterval(String paramString1, String paramString2)
  {
    Calendar localCalendar1 = getCalendar(paramString1);
    Calendar localCalendar2 = getCalendar(paramString2);
    if ((localCalendar1 == null) || (localCalendar2 == null)) {
      return 0;
    }
    return (int)((localCalendar2.getTimeInMillis() - localCalendar1.getTimeInMillis()) / 3600L / 24L / 1000L);
  }
  /**
   * 比较paramString1和paramString2相差的时间
   * paramInt:  8->English
   * */
  public static String timeInterval2(String paramString1, String paramString2, int paramInt)
  {
    String str = "";
    long l1 = timeInterval(paramString1, paramString2);
    if (l1 >= 0L)
    {
      long l2 = 0L;
      long l3 = 0L;
      long l4 = 0L;
      long l5 = 0L;
      
      l2 = l1 / 86400L;
      l3 = l1 % 86400L / 3600L;
      l4 = l1 % 3600L / 60L;
      l5 = l1 % 60L;
      if (paramInt == 8)
      {
        if (l2 > 0L) {
          str = String.valueOf(l2) + "days " + String.valueOf(l3) + "hours " + String.valueOf(l4) + "minutes " + String.valueOf(l5) + "seconds";
        } else if (l3 > 0L) {
          str = String.valueOf(l3) + "hours " + String.valueOf(l4) + "minutes " + String.valueOf(l5) + "seconds";
        } else if (l4 > 0L) {
          str = String.valueOf(l4) + "minutes " + String.valueOf(l5) + "seconds";
        } else if (l5 >= 0L) {
          str = String.valueOf(l5) + "seconds";
        }
      }
      else if (l2 > 0L) {
        str = String.valueOf(l2) + "天" + String.valueOf(l3) + "小时" + String.valueOf(l4) + "分" + String.valueOf(l5) + "秒";
      } else if (l3 > 0L) {
        str = String.valueOf(l3) + "小时" + String.valueOf(l4) + "分" + String.valueOf(l5) + "秒";
      } else if (l4 > 0L) {
        str = String.valueOf(l4) + "分" + String.valueOf(l5) + "秒";
      } else if (l5 >= 0L) {
        str = String.valueOf(l5) + "秒";
      }
    }
    return str;
  }
  //获取星期
  public static int dateWeekday(String paramString)
  {
    Calendar localCalendar = getCalendar(paramString);
    if (localCalendar == null) {
      return -1;
    }
    return localCalendar.get(7) - 1;
  }
  //获取日期  ，31号
  public static int dateMonthday(String paramString)
  {
    Calendar localCalendar = getCalendar(paramString);
    if (localCalendar == null) {
      return -1;
    }
    return localCalendar.get(5);
  }
  //获取当前年月日时分秒
  public static String getCurrentTimeString()
  {
    String str = "yyyy'-'MM'-'dd' 'HH:mm:ss";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    Calendar localCalendar = Calendar.getInstance();
    
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  //获取当前年月日
  public static String getCurrentDateString()
  {
    String str = "yyyy'-'MM'-'dd";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    Calendar localCalendar = Calendar.getInstance();
    
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  //获取当前时分秒
  public static String getOnlyCurrentTimeString()
  {
    String str = "HH:mm:ss";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    Calendar localCalendar = Calendar.getInstance();
    return localSimpleDateFormat.format(localCalendar.getTime());
  }
  //将Date转换为String类型的年月日时分秒
  public static String getTimeString(Date paramDate)
  {
    String str = "yyyy'-'MM'-'dd' 'HH:mm:ss";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    
    return localSimpleDateFormat.format(paramDate);
  }
  //将Calendar.getTime()转换为String类型的年月日时分秒
  public static String getTimeString(Calendar paramCalendar)
  {
    String str = "yyyy'-'MM'-'dd' 'HH:mm:ss";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    
    return localSimpleDateFormat.format(paramCalendar.getTime());
  }
  //将Date转换为String类型的年月日
  public static String getDateString(Date paramDate)
  {
    String str = "yyyy'-'MM'-'dd";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    
    return localSimpleDateFormat.format(paramDate);
  }
  /**
   * paramString1:具体时间
   * paramString2:转换类型
   * */
  public static Date getString2Date(String paramString1, String paramString2)
  {
    Date localDate = null;
    try
    {
      localDate = new SimpleDateFormat(paramString2).parse(paramString1);
    }
    catch (Exception localException)
    {
      localDate = null;
    }
    return localDate;
  }
  //将Calendar.getTime()转换为String类型的年月日
  public static String getDateString(Calendar paramCalendar)
  {
    String str = "yyyy'-'MM'-'dd";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
    
    return localSimpleDateFormat.format(paramCalendar.getTime());
  }
  //将Date按paramString类型转换为string
  public static String getFormartString(Date paramDate, String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
    return localSimpleDateFormat.format(paramDate);
  }
  //将Calendar按paramString类型转换为String
  public static String getFormartString(Calendar paramCalendar, String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
    return localSimpleDateFormat.format(paramCalendar.getTime());
  }
  //根据输入的String时间格式装载到Calendar,格式不对返回null
  public static Calendar getCalendar(String paramString)
  {
    int i = paramString.length();
    switch (i)
    {
    case 19: //2018-12-31 11:33:00
      return getCalendar(paramString, "yyyy'-'MM'-'dd' 'HH:mm:ss");
    case 10: //2018-12-31
      return getCalendar(paramString, "yyyy'-'MM'-'dd");
    }
    return null;
  }
  
  public static Calendar getCalendar(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    
    Calendar localCalendar = Calendar.getInstance();
    try
    {
      localCalendar.setTime(localSimpleDateFormat.parse(paramString1));
    }
    catch (ParseException localParseException)
    {
      return null;
    }
    return localCalendar;
  }
  //获取季度
  public static String getCurrentSeason()
  {
    String str1 = getFormartString(Calendar.getInstance(), "MM");
    String str2 = "";
    if ((str1.equals("01")) || (str1.equals("02")) || (str1.equals("03"))) {
      str2 = "1";
    } else if ((str1.equals("04")) || (str1.equals("05")) || (str1.equals("06"))) {
      str2 = "2";
    } else if ((str1.equals("07")) || (str1.equals("08")) || (str1.equals("09"))) {
      str2 = "3";
    } else if ((str1.equals("10")) || (str1.equals("11")) || (str1.equals("12"))) {
      str2 = "4";
    }
    return str2;
  }
  //计算一个月有多少个星期一
  public static int getWeeksOfMonth(String paramString, int paramInt)
  {
    int i = 0;
    int j = 30;
    String str1 = "";
    String str2 = "";
    str2 = "" + paramInt;
    str1 = paramString + "-" + str2 + "-" + "01";
    Calendar localCalendar = getCalendar(str1);
    j = localCalendar.getActualMaximum(5);
    for (int k = 0; k < j; k++)
    {
      String str3 = "" + (k + 1);
      String str4 = paramString + "-" + str2 + "-" + str3;
      if (dateWeekday(str4) == 1) {
        i++;
      }
    }
    return i;
  }
  //paramInt为年份，获取某一年周的
  public static int getMaxWeekNumOfYear(int paramInt)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.set(paramInt, 11, 31, 23, 59, 59);
    
    return getWeekOfYear(localGregorianCalendar.getTime());
  }
  
  public static int getWeekOfYear(Date paramDate)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setFirstDayOfWeek(2);
    localGregorianCalendar.setMinimalDaysInFirstWeek(7);
    localGregorianCalendar.setTime(paramDate);
    
    return localGregorianCalendar.get(3);
  }
  
  public static int getWeekOfYear(Date paramDate, int paramInt)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setFirstDayOfWeek(paramInt);
    localGregorianCalendar.setMinimalDaysInFirstWeek(7);
    localGregorianCalendar.setTime(paramDate);
    
    return localGregorianCalendar.get(3);
  }
  
  public static Date getFirstDayOfWeek(int paramInt1, int paramInt2)
  {
    GregorianCalendar localGregorianCalendar1 = new GregorianCalendar();
    localGregorianCalendar1.set(1, paramInt1);
    localGregorianCalendar1.set(2, 0);
    localGregorianCalendar1.set(5, 1);
    
    GregorianCalendar localGregorianCalendar2 = (GregorianCalendar)localGregorianCalendar1.clone();
    localGregorianCalendar2.add(5, paramInt2 * 7);
    
    return getFirstDayOfWeek(localGregorianCalendar2.getTime());
  }
  
  public static Date getLastDayOfWeek(int paramInt1, int paramInt2)
  {
    GregorianCalendar localGregorianCalendar1 = new GregorianCalendar();
    localGregorianCalendar1.set(1, paramInt1);
    localGregorianCalendar1.set(2, 0);
    localGregorianCalendar1.set(5, 1);
    
    GregorianCalendar localGregorianCalendar2 = (GregorianCalendar)localGregorianCalendar1.clone();
    localGregorianCalendar2.add(5, paramInt2 * 7);
    
    return getLastDayOfWeek(localGregorianCalendar2.getTime());
  }
  
  public static Date getFirstDayOfWeek(Date paramDate)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setFirstDayOfWeek(2);
    localGregorianCalendar.setTime(paramDate);
    localGregorianCalendar.set(7, localGregorianCalendar.getFirstDayOfWeek());
    return localGregorianCalendar.getTime();
  }
  
  public static Date getLastDayOfWeek(Date paramDate)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setFirstDayOfWeek(2);
    localGregorianCalendar.setTime(paramDate);
    localGregorianCalendar.set(7, localGregorianCalendar.getFirstDayOfWeek() + 6);
    return localGregorianCalendar.getTime();
  }
  
  public static int getDayOfWeek(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date localDate = null;
    try
    {
      localDate = localSimpleDateFormat.parse(paramString);
    }
    catch (Exception localException) {}
    return localDate.getDay();
  }
  
  public static String SetDateFormat(String paramString1, String paramString2)
    throws ParseException
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    String str = localSimpleDateFormat.format(localSimpleDateFormat.parse(paramString1));
    
    return str;
  }
  
  public static String getYearMonthFirstDay(int paramInt1, int paramInt2)
    throws ParseException
  {
    String str1 = Integer.toString(paramInt1);
    String str2 = Integer.toString(paramInt2);
    String str3 = "1";
    String str4 = str1 + "-" + str2 + "-" + str3;
    return SetDateFormat(str4, "yyyy-MM-dd");
  }
  
  public static boolean isLeapYear(int paramInt)
  {
    boolean bool = false;
    if ((paramInt % 4 == 0) && (paramInt % 100 != 0)) {
      bool = true;
    } else if (paramInt % 400 == 0) {
      bool = true;
    } else {
      bool = false;
    }
    return bool;
  }
  
  public static String getYearMonthEndDay(int paramInt1, int paramInt2)
    throws ParseException
  {
    String str1 = Integer.toString(paramInt1);
    String str2 = Integer.toString(paramInt2);
    String str3 = "31";
    if ((str2.equals("1")) || (str2.equals("3")) || (str2.equals("5")) || (str2.equals("7")) || (str2.equals("8")) || (str2.equals("10")) || (str2.equals("12"))) {
      str3 = "31";
    }
    if ((str2.equals("4")) || (str2.equals("6")) || (str2.equals("9")) || (str2.equals("11"))) {
      str3 = "30";
    }
    if (str2.equals("2")) {
      if (isLeapYear(paramInt1)) {
        str3 = "29";
      } else {
        str3 = "28";
      }
    }
    String str4 = str1 + "-" + str2 + "-" + str3;
    return SetDateFormat(str4, "yyyy-MM-dd");
  }
  
  public static int getMaxDays(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    boolean bool = isLeapYear(paramInt1);
    if (paramInt3 == 0)
    {
      if (bool == true) {
        i = 366;
      } else {
        i = 365;
      }
    }
    else if (paramInt3 == 1)
    {
      if ((paramInt2 == 1) || (paramInt2 == 3) || (paramInt2 == 5) || (paramInt2 == 7) || (paramInt2 == 8) || (paramInt2 == 10) || (paramInt2 == 12)) {
        i = 31;
      } else if ((paramInt2 == 4) || (paramInt2 == 6) || (paramInt2 == 9) || (paramInt2 == 11)) {
        i = 30;
      } else if (paramInt2 == 2) {
        if (bool == true) {
          i = 29;
        } else {
          i = 28;
        }
      }
    }
    else if (paramInt3 == 2) {
      if ((paramInt2 == 1) || (paramInt2 == 2) || (paramInt2 == 3))
      {
        if (bool == true) {
          i = 91;
        } else {
          i = 90;
        }
      }
      else if ((paramInt2 == 4) || (paramInt2 == 5) || (paramInt2 == 6)) {
        i = 91;
      } else if ((paramInt2 == 7) || (paramInt2 == 8) || (paramInt2 == 9)) {
        i = 92;
      } else if ((paramInt2 == 10) || (paramInt2 == 11) || (paramInt2 == 12)) {
        i = 92;
      }
    }
    return i;
  }
  
  public static String getQuarterMonth(int paramInt)
  {
    String str = "";
    if ((1 == paramInt) || (2 == paramInt) || (3 == paramInt)) {
      str = "(1,2,3)";
    }
    if ((4 == paramInt) || (5 == paramInt) || (6 == paramInt)) {
      str = "(4,5,6)";
    }
    if ((7 == paramInt) || (8 == paramInt) || (9 == paramInt)) {
      str = "(7,8,9)";
    }
    if ((10 == paramInt) || (11 == paramInt) || (12 == paramInt)) {
      str = "(10,11,12)";
    }
    return str;
  }
  
  public static String getMonthBeginDay()
  {
    String str = "";
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(5, 1);
    Date localDate = localCalendar.getTime();
    str = localSimpleDateFormat.format(localDate);
    return str;
  }
  
  public static String getLastMonthBeginDay()
  {
    String str = "";
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(2, -1);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(5, 1);
    Date localDate = localCalendar.getTime();
    str = localSimpleDateFormat.format(localDate);
    return str;
  }
  
  public static String getMonthEndDay()
  {
    String str = "";
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(5, 1);
    localCalendar.roll(5, -1);
    Date localDate = localCalendar.getTime();
    str = localSimpleDateFormat.format(localDate);
    return str;
  }
  
  public static String getLastMonthEndDay()
  {
    String str = "";
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(5, 0);
    Date localDate = localCalendar.getTime();
    str = localSimpleDateFormat.format(localDate);
    return str;
  }
  
  public static String getMonthBeginDay(String paramString)
  {
    String str = "";
    Calendar localCalendar = getCalendar(paramString);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(5, 1);
    Date localDate = localCalendar.getTime();
    str = localSimpleDateFormat.format(localDate);
    return str;
  }
  
  public static String getMonthEndDay(String paramString)
  {
    String str = "";
    Calendar localCalendar = getCalendar(paramString);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(5, 1);
    localCalendar.roll(5, -1);
    Date localDate = localCalendar.getTime();
    str = localSimpleDateFormat.format(localDate);
    return str;
  }
  
  public static String getDateByOption(String paramString1, String paramString2)
  {
    if ((paramString1.equals("")) || (paramString1.equals("0"))) {
      return "";
    }
    if (paramString1.equals("1")) {
      return getCurrentDateString();
    }
    Calendar localCalendar;
    SimpleDateFormat localSimpleDateFormat;
    int i;
    int j;
    if (paramString2.equals("0"))
    {
      if (paramString1.equals("2")) {
        return getDateString(getFirstDayOfWeek(new Date()));
      }
      if (paramString1.equals("3")) {
        return getMonthBeginDay();
      }
      if (paramString1.equals("7")) {
        return getLastMonthBeginDay();
      }
      if (paramString1.equals("4"))
      {
        localCalendar = Calendar.getInstance();
        localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        i = getQuarterInMonth(localCalendar.get(2), true);
        j = localCalendar.get(2);
        
        localCalendar.set(2, i);
        localCalendar.set(5, 1);
        return localSimpleDateFormat.format(localCalendar.getTime());
      }
      if (paramString1.equals("5"))
      {
        localCalendar = Calendar.getInstance();
        localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localCalendar.set(2, 0);
        localCalendar.set(5, 1);
        return localSimpleDateFormat.format(localCalendar.getTime());
      }
      if (paramString1.equals("8")) {
        return getFirstDayOfLastYear();
      }
    }
    else
    {
      if (paramString1.equals("2")) {
        return getDateString(getLastDayOfWeek(new Date()));
      }
      if (paramString1.equals("3")) {
        return getMonthEndDay();
      }
      if (paramString1.equals("7")) {
        return getLastMonthEndDay();
      }
      if (paramString1.equals("4"))
      {
        localCalendar = Calendar.getInstance();
        localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        i = getQuarterInMonth(localCalendar.get(2), false);
        j = localCalendar.get(2);
        

        localCalendar.set(2, i + 1);
        localCalendar.set(5, 0);
        return localSimpleDateFormat.format(localCalendar.getTime());
      }
      if (paramString1.equals("5"))
      {
        localCalendar = Calendar.getInstance();
        localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localCalendar.set(2, 12);
        localCalendar.set(5, 0);
        return localSimpleDateFormat.format(localCalendar.getTime());
      }
      if (paramString1.equals("8")) {
        return getEndDayOfLastYear();
      }
    }
    return "";
  }
  
  public static String getWeekBeginDay()
  {
    String str = "";
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(7, 1);
    str = localSimpleDateFormat.format(localCalendar.getTime());
    return str;
  }
  
  public static String getWeekBeginDay(String paramString)
  {
    String str = "";
    Calendar localCalendar = getCalendar(paramString);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(7, 1);
    str = localSimpleDateFormat.format(localCalendar.getTime());
    return str;
  }
  
  public static String getWeekEndDay()
  {
    String str = "";
    Calendar localCalendar = Calendar.getInstance();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(7, 7);
    str = localSimpleDateFormat.format(localCalendar.getTime());
    return str;
  }
  
  public static String getWeekEndDay(String paramString)
  {
    String str = "";
    Calendar localCalendar = getCalendar(paramString);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    localCalendar.set(7, 7);
    str = localSimpleDateFormat.format(localCalendar.getTime());
    return str;
  }
  
  private static int getQuarterInMonth(int paramInt, boolean paramBoolean)
  {
    paramInt += 1;
    int[] arrayOfInt = { 0, 3, 6, 9 };
    if (!paramBoolean) {
      arrayOfInt = new int[] { 2, 5, 8, 11 };
    }
    if ((paramInt >= 1) && (paramInt <= 3)) {
      return arrayOfInt[0];
    }
    if ((paramInt >= 4) && (paramInt <= 6)) {
      return arrayOfInt[1];
    }
    if ((paramInt >= 7) && (paramInt <= 9)) {
      return arrayOfInt[2];
    }
    return arrayOfInt[3];
  }
  
  public static String getFirstDayOfTheYear()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    localGregorianCalendar.set(6, 1);
    return localSimpleDateFormat.format(localGregorianCalendar.getTime());
  }
  
  public static String getFirstDayOfLastYear()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    localGregorianCalendar.add(1, -1);
    localGregorianCalendar.set(6, 1);
    return localSimpleDateFormat.format(localGregorianCalendar.getTime());
  }
  
  public static String getLastDayOfYear()
    throws ParseException
  {
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    int i = localGregorianCalendar.get(1);
    String str = getYearMonthEndDay(i, 11);
    return str + " 23:59:59";
  }
  
  public static String getEndDayOfLastYear()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    localGregorianCalendar.set(6, 0);
    return localSimpleDateFormat.format(localGregorianCalendar.getTime());
  }
  
  public static String getFirstDayOfSeason()
  {
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    int i = localGregorianCalendar.get(2) + 1;
    int j = localGregorianCalendar.get(1);
    String str = "01";
    if ((i >= 1) && (i <= 3)) {
      str = "01";
    }
    if ((i >= 4) && (i <= 6)) {
      str = "04";
    }
    if ((i >= 7) && (i <= 9)) {
      str = "07";
    }
    if ((i >= 10) && (i <= 12)) {
      str = "10";
    }
    return j + "-" + str + "-01";
  }
  
  public static String getLastDayDayOfSeason()
    throws ParseException
  {
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    int i = localGregorianCalendar.get(2) + 1;
    int j = localGregorianCalendar.get(1);
    int k = 3;
    if ((i >= 1) && (i <= 3)) {
      k = 3;
    }
    if ((i >= 4) && (i <= 6)) {
      k = 6;
    }
    if ((i >= 7) && (i <= 9)) {
      k = 9;
    }
    if ((i >= 10) && (i <= 12)) {
      k = 12;
    }
    String str = getYearMonthEndDay(j, k);
    return str + " 23:59:59";
  }
  
  public static String getFirstDayOfMonth()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    localGregorianCalendar.set(5, 1);
    return localSimpleDateFormat.format(localGregorianCalendar.getTime());
  }
  
  public static String getLastDayOfMonth()
    throws ParseException
  {
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    int i = localGregorianCalendar.get(1);
    int j = localGregorianCalendar.get(2);
    String str = getYearMonthEndDay(i, j + 1);
    return str + " 23:59:59";
  }
  
  public static String getFirstDayOfWeek()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    localGregorianCalendar.set(7, 2);
    return localSimpleDateFormat.format(localGregorianCalendar.getTime());
  }
  
  public static String getLastDayOfWeek()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    localGregorianCalendar.setTime(new Date());
    localGregorianCalendar.add(4, 1);
    localGregorianCalendar.set(7, 1);
    return localSimpleDateFormat.format(localGregorianCalendar.getTime()) + " 23:59:59";
  }
  
  public static String getToday()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return localSimpleDateFormat.format(new Date());
  }
  
  public static String getDayOfMonth()
  {
    GregorianCalendar localGregorianCalendar = (GregorianCalendar)Calendar.getInstance();
    int i = localGregorianCalendar.get(5);
    return i + "";
  }
}
