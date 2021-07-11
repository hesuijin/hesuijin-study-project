package com.example.demo.result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * JDK8 线程安全时间工具类
 * @author hzh 2018/2/3
 */
public class TimeUtils {

	public static final String ZONE_UTC = "UTC";

	/**
	 * 时间格式化规则
	 */
	public interface Pattern {
		DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");
		DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter YMD = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		DateTimeFormatter YYYYMMDDHH = DateTimeFormatter.ofPattern("yyyyMMddHH");
		DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter YYYYMMDDHHMMSSSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		DateTimeFormatter YYMMDDHHMMSSSSS = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
		DateTimeFormatter YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		DateTimeFormatter MMDDHHMM = DateTimeFormatter.ofPattern("MM/dd HH:mm");
		DateTimeFormatter YYMMDDHHMM = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		DateTimeFormatter YYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 获取UTC时间
	 *
	 * @return
	 * @author hzh
	 */
	public static String getUTCZonedDateTime() {
		return ZonedDateTime.now(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取UTC时间
	 * @param dateTimeFormatter
	 * @return
	 * @author hzh
	 */
	public static String getUTCTime(DateTimeFormatter dateTimeFormatter) {
		return ZonedDateTime.now(ZoneId.of(ZONE_UTC)).format(dateTimeFormatter);
	}

	/**
	 * 获取UTC时间
	 * @return
	 * @author hzh
	 */
	public static String getUTCTime() {
		return getUTCTime(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区当前时间
	 * @param zoneId
	 * @return
	 * @author hzh
	 */
	public static ZonedDateTime getLocalTimeByZoneId(ZoneId zoneId) {
		// 获取当前时间的时间戳，即UTC时间戳
		Instant instant = Instant.now();
		// 获取指定时区时间
		return instant.atZone(zoneId);
	}

	/**
	 * 获取指定时区当前时间
	 * @param zoneId
	 * @param dateTimeFormatter
	 * @return
	 * @author hzh
	 */
	public static String getLocalTimeByZoneId(ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
		// 获取指定时区时间
		ZonedDateTime zonedDateTime = getLocalTimeByZoneId(zoneId);
		// 按指定格式返回
		return zonedDateTime.format(dateTimeFormatter);
	}

	/**
	 * 获取指定时区当前时间
	 * @param zoneId
	 * @param dateTimeFormatter
	 * @return
	 * @author hzh
	 */
	public static String getNextMonthLocalTimeByZoneId(ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
		// 获取指定时区时间
		ZonedDateTime zonedDateTime = getLocalTimeByZoneId(zoneId);
		zonedDateTime = zonedDateTime.plusMonths(1L);
		// 按指定格式返回
		return zonedDateTime.format(dateTimeFormatter);
	}

	/**
	 * UTC时间转指定时区时间
	 * @param zonedDateTime
	 * @param zoneId
	 * @param dateTimeFormatter
	 * @return
	 * @author hzh
	 */
	public static String UTCToLocalTime(ZonedDateTime zonedDateTime, ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
		return zonedDateTime.toInstant().atZone(zoneId).format(dateTimeFormatter);
	}

	/**
	 * UTC时间转指定时区时间
	 * @param UTCTime
	 * @param zoneId
	 * @return
	 * @author hzh
	 */
	public static String UTCToZonedDateTime(String UTCTime, ZoneId zoneId) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(UTCTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		return instant.atZone(zoneId).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * UTC时间转指定时区时间
	 * @param UTCTime
	 * @param zoneId
	 * @return
	 * @author hzh
	 */
	public static String UTCToZonedDateTimes(String UTCTime, ZoneId zoneId) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(UTCTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		return instant.atZone(zoneId).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm"));
	}
	/**
	* @Description:  UTC时间转指定时间(格式YYYYMMDDHHMMSS)
	* @Param: [UTCTime, zoneId]
	* @return: java.lang.String
	* @Author: lzl
	* @Date: 2019/6/28 10:57
	*/
	public static String UTCToZonedDateTimeFormat(String UTCTime, ZoneId zoneId){
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(UTCTime, Pattern.YYYYMMDDHHMMSS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		return instant.atZone(zoneId).format(Pattern.YYYYMMDDHHMMSS);
	}
	/**
	 * UTC时间转指定时区时间
	 * @param UTCTime
	 * @param zoneId
	 * @return
	 * @author hzh
	 */
	public static ZonedDateTime UTC2ZonedDateTime(String UTCTime, ZoneId zoneId) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(UTCTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		return instant.atZone(zoneId);
	}

	/**
	 * UTC时间转指定时区时间
	 * @param UTCTime
	 * @param zoneId
	 * @param dateTimeFormatter
	 * @return
	 * @author hzh
	 */
	public static String UTCToZonedDateTime(String UTCTime, ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(UTCTime, dateTimeFormatter), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		return instant.atZone(zoneId).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * UTC时间转指定时区时间
	 * @param UTCTime
	 * @param zoneId
	 * @param dateTimeFormatter
	 * @return
	 * @author hzh
	 */
	public static String UTCToZonedDateTimeFormatter(String UTCTime, ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(UTCTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		return instant.atZone(zoneId).format(dateTimeFormatter);
	}

	/**
	 * UTC时间转指定时区时间
	 * @param zonedDateTime
	 * @param zoneId
	 * @return
	 * @author hzh
	 */
	public static String UTCToLocalTime(ZonedDateTime zonedDateTime, ZoneId zoneId) {
		return UTCToLocalTime(zonedDateTime, zoneId, Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 本地时间转UTC时间
	 * @param localTime
	 * @param dateTimeFormatter
	 * @return
	 */
	public static ZonedDateTime localTimeToUtc(String localTime, DateTimeFormatter dateTimeFormatter) {
		LocalDateTime localDateTime = LocalDateTime.parse(localTime, dateTimeFormatter);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return instant.atZone(ZoneId.of(ZONE_UTC));
	}

	/**
	 * 本地时间转UTC时间
	 * @param localTime
	 * @param zone
	 * @return
	 */
	public static String localTimeToUtc(String localTime,ZoneId zone) {
		LocalDateTime localDateTime = LocalDateTime.parse(localTime, TimeUtils.Pattern.YYYY_MM_DD_HH_MM_SS);
		Instant instant = localDateTime.atZone(zone).toInstant();
		return instant.atZone(ZoneId.of(ZONE_UTC)).format(TimeUtils.Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 本地时间转UTC时间
	 * @param localTime
	 * @return
	 */
	public static String localTimeToUtc(String localTime) {
		ZonedDateTime zonedDateTime = localTimeToUtc(localTime, Pattern.YYYY_MM_DD_HH_MM_SS);
		return zonedDateTime.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取时间秒数差
	 * @param utcTime
	 * @return
	 */
	public static int getTimeSecondBetween(String utcTime) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		long start = instant.toEpochMilli();
		Instant now = Instant.now();
		long end = now.toEpochMilli();

		long temp = end - start;

		return (int) ((temp) / 1000);
	}

	/**
	 * 获取指定小时后的时间戳
	 * @param utcTime
	 * @param hour
	 * @return
	 */
	public static long getTimeMills(String utcTime, long hour) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(hour, ChronoUnit.HOURS);
		Instant instant = utc.toInstant();

		return instant.toEpochMilli();
	}

	/**
	 * 获取指定天数前的时间
	 * @param utcTime
	 * @param day
	 * @return
	 */
	public static String getDateBeforeDay(String utcTime, long day) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(- day, ChronoUnit.DAYS);

		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定天数后的时间戳
	 * @param utcTime
	 * @param day
	 * @return
	 */
	public static String getTimeAfterDay(String utcTime, long day) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(day, ChronoUnit.DAYS);

		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	public static String getTimeAfterMonths(String utcTime, long months) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(months, ChronoUnit.MONTHS);

		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定分钟后的时间戳
	 * @param utcTime
	 * @param minute
	 * @return
	 */
	public static String getTimeAfterMinutes(String utcTime, long minute) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(minute, ChronoUnit.MINUTES);

		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}


	/**
	 * 获取指定小时后的时间戳
	 * @param utcTime
	 * @param hour
	 * @return
	 */
	public static String getTimeAfterHours(String utcTime, long hour) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(hour, ChronoUnit.HOURS);

		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定秒后的时间
	 * @param utcTime
	 * @param second
	 * @return
	 */
	public static String getTimeAfterSecond(String utcTime, long second) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(second, ChronoUnit.SECONDS);

		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定秒后的时间戳
	 * @param utcTime
	 * @param second
	 * @return
	 */
	public static long getTimeMillsAfterSecond(String utcTime, long second) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(second, ChronoUnit.SECONDS);
		Instant instant = utc.toInstant();

		return instant.toEpochMilli();
	}

	/**
	 * 获取时间秒数差
	 * @param utcBeginTime
	 * @param utcEndTime
	 * @return
	 */
	public static int getTimeSecondBetween(String utcBeginTime, String utcEndTime) {
		ZonedDateTime utcBegin = ZonedDateTime.of(LocalDateTime.parse(utcBeginTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		ZonedDateTime utcEnd = ZonedDateTime.of(LocalDateTime.parse(utcEndTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instantBegin = utcBegin.toInstant();
		Instant instantEnd = utcEnd.toInstant();
		long start = instantBegin.toEpochMilli();
		long end = instantEnd.toEpochMilli();

		Long l = (end - start)/1000;
		return l.intValue();
		//return (int) (end - start) / 1000;
	}

	/**
	 * 获取时间秒数差
	 * @param utcBeginTime
	 * @param utcEndTime
	 * @return
	 */
	public static long getTimeSecondBetweenLong(String utcBeginTime, String utcEndTime) {
		ZonedDateTime utcBegin = ZonedDateTime.of(LocalDateTime.parse(utcBeginTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		ZonedDateTime utcEnd = ZonedDateTime.of(LocalDateTime.parse(utcEndTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instantBegin = utcBegin.toInstant();
		Instant instantEnd = utcEnd.toInstant();
		long start = instantBegin.toEpochMilli();
		long end = instantEnd.toEpochMilli();

		return (end - start) / 1000;
	}


	/**
	 * 获取指定秒前的时间
	 * @param utcTime
	 * @param second
	 * @return
	 */
	public static String getDateBeforeSecond(String utcTime, long second) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(- second, ChronoUnit.SECONDS);
		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定分前的时间
	 * @param utcTime
	 * @param minute
	 * @return
	 */
	public static String getDateBeforeMinute(String utcTime, long minute) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(- minute, ChronoUnit.MINUTES);
		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定小时前的时间
	 * @param utcTime
	 * @param hours
	 * @return
	 */
	public static String getDateBeforeHour(String utcTime, long hours) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		utc = utc.plus(- hours, ChronoUnit.HOURS);
		return utc.format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取utc时间与当前时间的天数差
	 * @param utcTime
	 * @return
	 */
	public static int getTimeDayBetween(String utcTime) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		ZonedDateTime now = ZonedDateTime.now(ZoneId.of(ZONE_UTC));

		long between = ChronoUnit.DAYS.between(utc, now);

		return (int) between;
	}
	/**
	 * 获取两个时间的天数差
	 * @param
	 * @return
	 */
	public static int getTimeDayBetweens(String beginTime,String endTime) {
		ZonedDateTime begin = ZonedDateTime.of(LocalDateTime.parse(beginTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		ZonedDateTime end = ZonedDateTime.of(LocalDateTime.parse(endTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		long between = ChronoUnit.DAYS.between(begin, end);

		return (int) between;
	}

	/**
	 * 获取utc时间与当前时间的分钟差
	 * @param utcTime
	 * @return
	 */
	public static int getTimeMinuteBetween(String utcTime) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		ZonedDateTime now = ZonedDateTime.now(ZoneId.of(ZONE_UTC));

		long between = ChronoUnit.MINUTES.between(utc, now);

		return (int) between;
	}

	/**
	 * 获取utc时间与当前时间的小时差
	 * @param utcTime
	 * @return
	 */
	public static int getTimeHourBetween(String utcTime) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		ZonedDateTime now = ZonedDateTime.now(ZoneId.of(ZONE_UTC));

		long between = ChronoUnit.HOURS.between(utc, now);

		return (int) between;
	}

	/**
	 * 获取两个时间的小时差
	 * @param
	 * @return
	 */
	public static int getTimeHourBetween(String beginTime, String endTime) {
		ZonedDateTime beginDateTime = ZonedDateTime.of(LocalDateTime.parse(beginTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		ZonedDateTime endTimeTime = ZonedDateTime.of(LocalDateTime.parse(endTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		long between = ChronoUnit.HOURS.between(beginDateTime, endTimeTime);

		return (int) between;
	}

	/**
	 * 获取utc时间与当前时间的分钟差
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getTimeMinuteBetween(String begin, String end) {
		ZonedDateTime beginDateTime = ZonedDateTime.of(LocalDateTime.parse(begin, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		ZonedDateTime endTimeTime = ZonedDateTime.of(LocalDateTime.parse(end, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));

		long between = ChronoUnit.MINUTES.between(beginDateTime, endTimeTime);

		return (int) between;
	}

	/**
	 * 判断日期是否为工作日
	 * @param utcDate
	 * @param zoneId
	 * @return
	 */
	public static boolean isWorkDay(String utcDate, ZoneId zoneId) {
		boolean isWorkDay = true;

		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcDate, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);

		DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();

		switch (dayOfWeek) {
			case SATURDAY:
			case SUNDAY:
				isWorkDay = false;
				break;
			default:
				break;
		}

		return isWorkDay;
	}

	/**
	 * 判断日期是是周几
	 * @param utcDate
	 * @param zoneId
	 * @return
	 */
	public static DayOfWeek getWeek(String utcDate, ZoneId zoneId) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcDate, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		Instant instant = utc.toInstant();
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);

		return zonedDateTime.getDayOfWeek();
	}

	/**
	 * 获取UTC时间戳
	 * @param utcTime
	 * @return
	 */
	public static long getTimestamp(String utcTime) {
		ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.parse(utcTime, Pattern.YYYY_MM_DD_HH_MM_SS), ZoneId.of(ZONE_UTC));
		return utc.toInstant().toEpochMilli();
	}

	/**
	 * UTC时间转时间戳
	 * @param time
	 * @return
	 */
	public static String getTimestamp(Long time){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZONE_UTC));
		Date date = new Date(time);
		return  simpleDateFormat.format(date);
	}

	/**
	 * 判断时间是否在范围内
	 * @param currentTime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isBetween(String currentTime, String startTime, String endTime) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date current = sdf.parse(currentTime);
			Date start = sdf.parse(startTime);
			Date end = sdf.parse(endTime);

			if (current.getTime() >= start.getTime() && current.getTime() <= end.getTime()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 判断时间是否在范围内
	 * @param currentTime
	 * @param time
	 * @return
	 */
	public static boolean isLarge(String currentTime, String time) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date current = sdf.parse(currentTime);
			Date start = sdf.parse(time);

			if (current.getTime() >= start.getTime()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 获取指定时区前几天的一日的utc开始时间
	 * @param beforeDay
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneBeginTimeBeforeDay(int beforeDay, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusDays(-beforeDay);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区前几天的一日的utc开始时间
	 * @param beforeDay
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneBeginTimeBeforeDayAndSecond(int beforeDay, int second, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusDays(-beforeDay);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());

		localDateTime = localDateTime.plusSeconds(second);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区前几天的一日的utc结束时间
	 * @param beforeDay
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneEndTimeBeforeDay(int beforeDay, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusDays(-beforeDay);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour()).plusHours(23L);
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute()).plusMinutes(59L);
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond()).plusSeconds(59L);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区前几天的一日的utc结束时间
	 * @param beforeDay
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneEndTimeBeforeDayAndSecond(int beforeDay, int second, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusDays(-beforeDay);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour()).plusHours(23L);
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute()).plusMinutes(59L);
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond()).plusSeconds(59L);
		localDateTime = localDateTime.plusSeconds(second);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区前几分钟的一日的utc开始时间
	 * @param beforeMinute
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneBeginTimeBeforeMinute(int beforeMinute, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());
		localDateTime = localDateTime.plusMinutes(-beforeMinute);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区前几月的一日的utc开始时间
	 * @param beforeMonth
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneBeginTimeBeforeMonth(int beforeMonth, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusMonths(-beforeMonth);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取指定时区当前月份的开始和结束时间
	 * @param zoneId
	 * @return
	 */
	public static String[] getStartTimeAndEndTimeCurrentMonth(ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusDays(-localDateTime.getDayOfMonth() + 1);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		String startTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);

		localDateTime = localDateTime.plusMonths(1);
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond() - 1);
		zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		String endTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);

		return new String[]{startTime, endTime};
	}


	/**
	 * 获取指定时区当前月份的开始和结束时间
	 * @param zoneId
	 * @return
	 */
	public static String[] getStartTimeAndEndTimeCurrentMonth2(ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		if (1 == localDateTime.getDayOfMonth()) {
			localDateTime = localDateTime.plusDays(-1);
		}
		localDateTime = localDateTime.plusDays(-localDateTime.getDayOfMonth() + 1);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		String startTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);

		localDateTime = localDateTime.plusMonths(1);
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond() - 1);
		zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		String endTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);

		return new String[]{startTime, endTime};
	}

	/**
	 * 获取指定时区当天的utc开始时间
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneBeginTime(ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}


	/**
	 * 获取指定时区当天的utc结束
	 * @param zoneId
	 * @return
	 */
	public static String getUtcOfZoneEndTime(ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour()).plusHours(23L);
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute()).plusMinutes(59L);
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond()).plusSeconds(59L);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		return zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	public static String localeTimeToUtc(String time, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.parse(time, Pattern.YYYY_MM_DD_HH_MM_SS);
		return localDateTime.atZone(zoneId).toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	* @Description:  获取指定日期当月开始和结束时间
	* @Param: [date, zoneId]
	* @return: java.lang.String[]
	* @Date: 2020/1/4 14:57
	*/
	public static String[] getMonthStartTimeAndEndTime(String date, ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.parse(date + "-01 00:00:00", Pattern.YYYY_MM_DD_HH_MM_SS);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

		ZonedDateTime startZoneDateTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC));
		String startTime = startZoneDateTime.format(Pattern.YYYY_MM_DD_HH_MM_SS);

		startZoneDateTime = ZonedDateTime.of(localDateTime.plusMonths(1), zoneId).toInstant().atZone(ZoneId.of(ZONE_UTC));
		startZoneDateTime = startZoneDateTime.plusSeconds(-startZoneDateTime.getSecond() - 1);

		String endTime = startZoneDateTime.format(Pattern.YYYY_MM_DD_HH_MM_SS);

		return new String[]{startTime, endTime};
	}

	/**
	* @Description: 获取当前时间一周的开始和结束时间
	* @Param: [str, zoneId]
	* @return: java.lang.String[]
	* @Date: 2020/1/4 15:28
	*/
	public static String[] getWeekStartTimeAndEndTime(String str, ZoneId zoneId) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String beginTime = "";
		String endTime = "";
		try {
			Date date = formatter.parse(str);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayofweek == 1) {
				dayofweek += 7;
			}
			cal.add(Calendar.DATE, 2 - dayofweek);
			beginTime = TimeUtils.localeTimeToUtc(formatter.format(cal.getTime()),zoneId);
			endTime = 	TimeUtils.localeTimeToUtc(TimeUtils.subDay(beginTime,7),zoneId);
		}catch (Exception e) {
			e.getMessage();
		}
		return new String[]{beginTime, endTime};
	}


	/**
	 * 当前时间获取上一月份开始及结束时间
	 * @param zoneId
	 * @return
	 */
	public static String[] getStartTimeAndEndTimeLastMonth(ZoneId zoneId) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusDays(-localDateTime.getDayOfMonth() + 1);
		localDateTime = localDateTime.plusHours(-localDateTime.getHour());
		localDateTime = localDateTime.plusMinutes(-localDateTime.getMinute());
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond());
		localDateTime = localDateTime.plusMonths(-1);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		String startTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);

		localDateTime = localDateTime.plusMonths(1);
		localDateTime = localDateTime.plusSeconds(-localDateTime.getSecond() - 1);
		zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		String endTime = zonedDateTime.toInstant().atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS);

		return new String[]{startTime, endTime};
	}

	public static String getLastMonth(ZoneId zoneId){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId);
		localDateTime = localDateTime.plusMonths(-1);
		return localDateTime.format(TimeUtils.Pattern.YYYY_MM);
	}


	/**
	 * 獲取當前日期的月份開始和結束時間
	 * @param localTime
	 * @param dateTimeFormatter
	 * @return
	 */
	public static String[] getMonthBeginAndEndByLocalTime(String localTime, DateTimeFormatter dateTimeFormatter) {
		localTime = localTime.substring(0, 7) + "-01 00:00:00";
		LocalDateTime localDateTime = LocalDateTime.parse(localTime, dateTimeFormatter);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(ZONE_UTC));

		String begin = zonedDateTime.format(Pattern.YYYY_MM_DD_HH_MM_SS);

		zonedDateTime = zonedDateTime.plusMonths(1).plusSeconds(-1);

		switch (zonedDateTime.getMonthValue()) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				zonedDateTime = zonedDateTime.plusDays(1);
				break;
			default:
				break;
		}

		String end = zonedDateTime.format(Pattern.YYYY_MM_DD_HH_MM_SS);

		return new String[]{begin, end};
	}

	/**
	* @Description: 将一段时间转为数组
	* @Param: [startTime, endTime]
	* @return: java.util.List<java.lang.String>
	* @Author: lzl
	* @Date: 2019/5/14 14:29
	*/
	public static List<String> getListByTime(String startTime, String endTime)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<>();
		list.add(startTime.substring(0,10));
		try {
			Date begin = sdf.parse(startTime);
			Date ends = sdf.parse(endTime);
			while(true) {
				begin.setTime(begin.getTime() + (long)60l*60*24*1000 );
				if( begin.getTime() > ends.getTime() ) {
					break;
				}
				list.add(sdf.format(begin));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getListByTimes(String startTime,String endTime)  {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> list = new ArrayList<>();
		list.add(startTime);
		try {
			Date begin = sdf.parse(startTime);
			Date ends = sdf.parse(endTime);
			while(true) {
				begin.setTime(begin.getTime() + (long)60l*60*24*1000 );
				if( begin.getTime() > ends.getTime() ) {
					break;
				}
				list.add(sdf.format(begin));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	* @Description: 将当前时间加上一天
	* @Param: [date]
	* @return: java.lang.String
	* @Author: lzl
	* @Date: 2019/5/15 10:34
	*/

	public   static String subDay(String date,Integer days)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_MONTH, days);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	/**
	 * @Description: 将当前时间加上秒数
	 * @Param: [date]
	 * @return: java.lang.String
	 * @Author: lzl
	 * @Date: 2019/5/15 10:34
	 */

	public  static String subSecond(String date,Integer second)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.SECOND, second);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}


	/**
	 * @Description: 将当前时间加上秒数
	 * @Param: [date]
	 * @return: java.lang.String
	 * @Author: lzl
	 * @Date: 2019/5/15 10:34
	 */

	public  static String subSecond2(String date,Integer second)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.SECOND, second);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}
	public   static String subDays(String date,Integer days)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_MONTH, days);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}
	/**
	* @Description: 将当前时间加上小时
	* @Param: [date]
	* @return: java.lang.String
	* @Date: 2019/10/14 10:47
	*/
	public   static String dataAddHour(String date,Integer hour)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.HOUR, hour);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	public static  Date stringToDate(String times){
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			 date = sdf.parse(times);
		}catch (Exception e){
			e.printStackTrace();
		}
		return date;
	}

	/**
	* @Description: 根据所在地区获取相差的utc时间
	* @Param: [str]
	* @return: java.lang.Integer
	* @Author: lzl
	* @Date: 2019/6/6 0:06
	*/

	public static Integer getLocalHour(String str){
		Integer hour = 0;
		switch (str){
			case "th_TH":
				hour = 7;
				break;
			case "ja_JP":
				hour = 9;
				break;
				default:
					hour = 8;
		}
		return hour;
	}
	/**
	* @Description: 比较两个时间的大小
	* @Param: [nowTime, time]
	* @return: int
	* @Author: lzl
	* @Date: 2019/7/22 10:20
	*/
	public static int compareTime(String nowTime,String time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date bt = null;
		Date et = null;
		try {
			 bt = sdf.parse(nowTime);
			 et = sdf.parse(time);
		}catch (Exception e){
			e.printStackTrace();
		}
			return bt.compareTo(et);
	}

	/**
	 * @Description: 比较两个时间的大小
	 * @Param: [nowTime, time]
	 * @return: int
	 * @Author: lzl
	 * @Date: 2019/7/22 10:20
	 */
	public static int compareTimeToSecond(String nowTime,String time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date bt = null;
		Date et = null;
		try {
			bt = sdf.parse(nowTime);
			et = sdf.parse(time);
		}catch (Exception e){
			e.printStackTrace();
		}
		return bt.compareTo(et);
	}


	public static int compareTimeByTwoHour(String time){
		String utcTime = getUTCTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		Date now = null;
		try {
			date = sdf.parse(utcTime);
			now = sdf.parse(time);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(now);
		//20分钟前的时间
		nowTime.add(Calendar.HOUR, 10);
		return nowTime.getTime().compareTo(date);
	}
	/**
	* @Description: 获取20分钟之前的时间
	* @Param: []
	* @return: java.lang.String
	* @Author: lzl
	* @Date: 2019/7/22 10:23
	*/
	public static String getTimeByTwentyMinutes(){
		String utcTime = TimeUtils.getUTCTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(utcTime);
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(date);
		//20分钟前的时间
		nowTime.add(Calendar.MINUTE, -20);
		return sdf.format(nowTime.getTime());
	}
	/**
	* @Description: 根据时间获取距离当前时间的天数
	* @Param: [smdate, bdate]
	* @return: int
	* @Author: lzl
	* @Date: 2019/7/16 9:39
	*/
    public static int daysBetween(String smdate,String bdate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        long between_days = 0;
        try {
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days = (time2-time1)/(1000*3600*24);
        }catch (Exception e){
            e.getMessage();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }



    /**
    * @Description: 获取当月天数
    * @Param: []
    * @return: int
    * @Author: lzl
    * @Date: 2019/7/16 10:25
    */
	public static int getCurrentMonthDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}


	/**
	 * 根据某个月的最后一天获取一整个月的数组
	 * @param someMonthLastDayDate 例  2020-01-31
	 * @return
	 */
	public static String[] getAllDaysBySomeMonthLastDay(LocalDate someMonthLastDayDate){
		LocalDate thisDay = someMonthLastDayDate;
		int lengthOfMonth = thisDay.lengthOfMonth();
		String[] dates = new String[lengthOfMonth];

		for (int i = lengthOfMonth ; i>0 ;i--){
			if (i == lengthOfMonth){
				dates[i - 1] = thisDay.toString();
			}else {
				dates[i - 1] = thisDay.plusDays(-1).toString();
				thisDay = thisDay.plusDays(-1);
			}
		}
		return dates;
	}


	/**
	 * 获取昨天到一号的的所有天数的数组
	 * @param yesterDay
	 * @return
	 */
	public static String[] getAllDaysByYesterday(LocalDate yesterDay){
		LocalDate thisDay = yesterDay;
		String[] dates = new String[thisDay.getDayOfMonth()];
		for (int i = thisDay.getDayOfMonth() ; i > 0 ; i--){
			if (i == thisDay.getDayOfMonth()){
				dates[i - 1] = thisDay.toString();
			}else {
				dates[i - 1] = thisDay.plusDays(-1).toString();
				thisDay = thisDay.plusDays(-1);
			}
		}
		return dates;
	}


	/**
	 * 获取某一天的开始时间和结束时间
	 * 例如: someDate = "2020-02-15"  zoneId = UTC+8
	 * 返回:2020-02-14 16:00:00
	 *      2020-02-15 15:59:59
	 * @param someDate
	 * @return
	 */
	public static String[] getSomeDateBeginAndEndTimes(String someDate,ZoneId zoneId){
		String beginDateTime = someDate + " 00:00:00";
		String endDateTime = someDate + " 23:59:59";
		ZonedDateTime beginUtc = ZonedDateTime.of(LocalDateTime.parse(beginDateTime, Pattern.YYYY_MM_DD_HH_MM_SS), zoneId);
		ZonedDateTime endUtc = ZonedDateTime.of(LocalDateTime.parse(endDateTime, Pattern.YYYY_MM_DD_HH_MM_SS), zoneId);
		Instant beginInstant = beginUtc.toInstant();
		Instant endInstant = endUtc.toInstant();
		return new String[]{beginInstant.atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS).toString(),
				endInstant.atZone(ZoneId.of(ZONE_UTC)).format(Pattern.YYYY_MM_DD_HH_MM_SS).toString()};
	}

	/**
	 * 时间戳转成指定时区时间
	 * @param time
	 * @param zoneId
	 * @return
	 */
	public static String long2StringByZoneId(Long time,ZoneId zoneId){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
		Date date = new Date(time);
		return  simpleDateFormat.format(date);
	}

	/**
	 * 时间戳转成指定时区日期
	 * @param time
	 * @param zoneId
	 * @return
	 */
	public static String long3StringByZoneId(Long time,ZoneId zoneId){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
		Date date = new Date(time);
		return  simpleDateFormat.format(date);
	}

	/**
	 * 指定时区时间,转成时间戳
	 * @param date
	 * @param zoneId
	 * @return
	 */
	public static Long string2LongByZoneId(String date,ZoneId zoneId){
		LocalDateTime localDateTime = LocalDateTime.parse(date, Pattern.YYYY_MM_DD_HH_MM_SS);
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId).toInstant().atZone(ZoneId.of(ZONE_UTC));
		long milli = zonedDateTime.toInstant().toEpochMilli();
		return milli;
	}

	/**
	 * 将时间转换成时间戳
	 * @param date
	 * @return
	 */
	public static Long string2LongUTC(String date){
		LocalDateTime localDateTime = LocalDateTime.parse(date, Pattern.YYYY_MM_DD_HH_MM_SS);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(ZONE_UTC)).toInstant().atZone(ZoneId.of(ZONE_UTC));
		long milli = zonedDateTime.toInstant().toEpochMilli();
		return milli;
	}


	/**
	* @Description: 判断字符串是否是指定日期格式
	* @Param: [str]
	* @return: boolean
	* @Date: 2020/3/13 17:44
	*/
	public static boolean isValidDate(String str){
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			format.setLenient(false);
			format.parse(str);
		}catch (ParseException e) {
			convertSuccess=false;
		}
		return convertSuccess;
	}

	/**
	* @Description: 将毫秒转为时间
	* @Param: [millSecond]
	* @return: java.lang.String
	* @Date: 2020/3/28 20:13
	*/
	public static String transferLongToDate(long millSecond) {
		Date time = new Date(millSecond);
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formats.format(time);
	}

	/**
	 * 将时间加上天数,尾数为23:59:59
	 * @param
	 * @return
	 */
	public static String addTimeForDays(String time,Integer days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(getUTCTime());
		}catch (Exception e){
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_MONTH, days);
		Date dt1 = rightNow.getTime();
		String reStr = sdf2.format(dt1);
		return reStr + " 23:59:59";
	}

	public static void main(String[] args) {
		long start = 1599868799000L;
		long end = 1609286400000L;

		Long l = (end - start)/1000;
		System.out.println(end - start);
		System.out.println((int)(end - start));

		System.out.println(l.intValue());

		System.out.println((int)(end - start) / 1000);


	}
}
