package com.wyb.jdk8;

import com.wyb.jdk8.lambda.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * 表示能应用在一组元素上一次执行的操作序列。Stream 操作分为中间操作或者最终操作两种，</br>
 * 最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样就可以将多个操作依次串起来。</br>
 * Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set， Map不支持。</br>
 * Stream的操作可以串行执行或者并行执行。
 */
public class JDK8StreamDemo {
    /**
     * 操作步骤： 创建 Stream : 一个数据源 (如 : 集合、数组)， 获取一个流</br>
     * 中间操作 : 一个中间操作链，对数据源的数据进行处理</br>
     * 终止操作(终端操作) : 一个终止操作，执行中间操作链，并产生结果
     */
    public static void main(String[] args) {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "aale", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "dale", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "gemale", 27, 1900));
                add(new Person("Maude", "Jaimie", "Java programmer", "hemale", 27, 1900));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        // 创建 Stream
        // a 根据集合对象创建
        javaProgrammers.stream(); // 获取一个顺序流
        javaProgrammers.parallelStream();// 获取一个并行流

        // b 根据数组创建
        Stream<Integer> stream1 = Arrays.stream(new Integer[10]);
        Stream<Person> stream11 = javaProgrammers.stream().limit(3);
        System.out.println(javaProgrammers.size() + "  " + stream11.count());

        // c 可以使用静态方法 Stream.of(), 通过显示值创建一个流，它可以接收任意数量的参数
        // 注 : Stream.of静态方法 底层就是 Arrays.stream 静态方法
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

        // d 由函数创建流 : 创建无限流
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);
        // 生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
        // stream4.forEach(p -> System.out.println(p));

        // Stream 的中间操作
        // 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理。而在终止操作时一次性全部处理，称为“惰性求值”
        // filter 接受Lambda，从流中排除某些元素
        Stream<Person> filterStream = javaProgrammers.stream().filter(p -> p.getSalary() > 1200);

        // limit 截断流，使其元素不超过某个给定数量
        Stream<Person> limitStream = filterStream.limit(7);

        // skip 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit互补
        Stream<Person> skipStream = limitStream.skip(2);

        // distinct 去重，通过hashcode和equals去重
        Stream<Person> distineStream = skipStream.distinct();

        // map 接受Lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        Stream<String> mapStream = distineStream.map(p -> p.getFirstName());

        // sorted 排序
        Stream<String> sortedStream = mapStream.sorted((p1, p2) -> p2.compareTo(p1));

        // 输出结果
        // sortedStream.forEach(p -> System.out.println(p));

        // ******************************* Stream的终止操作 S ************************
        // toArray 转换成数组流
        // Object[] array = sortedStream.toArray();

        // reduce 和大数据中的reduce有异曲同工之妙
        // 这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
        // 和前面 Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。
        // String reduceStr = sortedStream.reduce(" ", String::concat);
        // System.out.println("reduce：" + reduceStr);

        // anyMatch 有一些数据能匹配都返回true
        // boolean anyMatch = sortedStream.anyMatch(s -> s.startsWith("F"));
        // System.out.println("anyMatch：" + anyMatch);

        // allMatch 所有的数据都匹配才返回true
        // boolean allMatch = sortedStream.allMatch(s -> s.startsWith("F"));
        // System.out.println("allMatch：" + allMatch);

        // noneMatch 都不匹配就返回true
        // boolean noneMatch = sortedStream.noneMatch(s -> s.startsWith("F"));
        // System.out.println("noneMatch：" + noneMatch);

        // count 返回满足条件数据的条数
        long count = sortedStream.count();
        System.out.println("count：" + count);

        // min 满足条件的最小数
        // Optional<String> minOptional = sortedStream.min((a, b) -> a.compareTo(b));
        // System.out.println("minOptional：" + minOptional.get());

        // max 满足条件的最大数
        // Optional<String> maxOptional = sortedStream.max((a, b) -> a.compareTo(b));
        // System.out.println("maxOptional：" + maxOptional.get());

        // findFirst 找到第一条满足条件的数据
        // Optional<String> findFirstOptional = sortedStream.findFirst();
        // System.out.println("findFirstOptional：" + findFirstOptional.get());

        // findAny 找到满足条件的任意一条数据(在并行流中,可能数据结果不一致)
        // Optional<String> findAnyOptional = sortedStream.findAny();
        // System.out.println("findAnyOptional：" + findAnyOptional.get());

        // collect 该功能比较强大,可以进行数据的二次整合,如返回list,返回count,返回平均数等
        // List<String> list = sortedStream.collect(Collectors.toList());
        // list.stream().forEach(s -> System.out.println(s));

        // long collectCount = sortedStream.collect(Collectors.counting());
        // System.out.println("collectCount：" + collectCount);
    }
}
