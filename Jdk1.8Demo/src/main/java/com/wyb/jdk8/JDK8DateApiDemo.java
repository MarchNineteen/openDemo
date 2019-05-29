package com.wyb.jdk8;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * LocalDate、 LocalTime、 LocalDateTime类的实例是不可变的对象，分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。</br>
 * 它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。
 */
public class JDK8DateApiDemo {
    public static void main(String[] args) {
        // Clock 时钟
        // Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，可以用来取代 System.currentTimeMillis() 来获取当前的微秒数。
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println("Clock 当前的微秒数：" + millis);
        System.out.println("System当前的微秒数：" + System.currentTimeMillis());

        // Instant
        Instant instant = clock.instant();
        Instant instantNow = Instant.now();
        Date legacyDate = Date.from(instant); // legacy java.util.Date
        System.out.println("Instant 当前时间：" + legacyDate);
        System.out.println("Instant now时间：" + instantNow);
        System.out.println("Date    当前时间：" + new Date());

        // 在新API中时区使用ZoneId来表示。时区可以很方便的使用静态方法of来获取到。
        // 时区定义了到UTS时间的时间差，在Instant时间点对象到本地日期对象之间转换的时候是极其重要的。
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println("柏林格林威治时间时差（东一区）：" + zone1.getRules());
        System.out.println("巴西格林威治时间时差（西三区）：" + zone2.getRules());

        // LocalTime 定义了一个没有时区信息的时间，例如 晚上10点，或者 17:30:15。
        // 下面的例子使用前面代码创建的时区创建了两个本地时间。之后比较时间并以小时和分钟为单位计算两个时间的时间差：
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2)); // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println("hoursBetween" + hoursBetween); // -4
        System.out.println(minutesBetween);// -299

        // LocalTime 提供了多种工厂方法来简化对象的创建，包括解析时间字符串。
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);// 23:59:59

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime); // 13:37

        // LocalDateTime获取对象的方法
        // 方式1通过静态方法 now();
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("LocalDateTime [now]方法：" + ldt);
        // 方式2通过静态方法of()方法参数可以指定年月日时分秒
        LocalDateTime of = LocalDateTime.of(2018, 12, 30, 20, 20, 20);
        System.out.println("LocalDateTime [of]方法：" + of);
        System.out.println("LocalDateTime format结果：" + ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        // 转换的方法 toLocalDate();toLocalTime();
        LocalDate localDate = ldt.toLocalDate();
        System.out.println("LocalDate :" + localDate);
        LocalTime localTime = ldt.toLocalTime();
        System.out.println("LocalTime :" + localTime);

        // paser() 将一个日期字符串解析成日期对象,注意字符串日期的写法的格式要正确,否则解析失败
        LocalDateTime parse = LocalDateTime.parse("2007-12-03T10:15:30");
        System.out.println("LocalDateTime 解析结果：" + parse.getYear() + " " + parse.getMonthValue() + " "
                + parse.getDayOfMonth() + " " + parse.getHour() + " " + parse.getMinute() + " " + parse.getSecond());

        // 注意细节:如果用LocalDateTime 想按照我们的自定义的格式去解析,注意日期字符串的 年月日时分秒要写全,不然就报错
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt4 = LocalDateTime.parse("2018-01-21 20:30:36", formatter2);
        System.out.println("LocalDateTime 自定义格式转换：" + ldt4);

    }

}
