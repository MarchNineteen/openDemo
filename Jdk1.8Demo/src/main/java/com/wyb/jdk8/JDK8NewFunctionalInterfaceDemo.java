package com.wyb.jdk8;

import com.wyb.jdk8.lambda.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * (六）jdk1.8提供的新函数式接口
 */
public class JDK8NewFunctionalInterfaceDemo {
    public static void main(String[] args) {
        // 1 Predicate接口 断言型接口 只有一个参数，返回boolean类型。该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）
        Person person = new Person();
        Person person2 = new Person();
        Predicate<Person> predicate = p -> {
            if (p.getAge() > 12) {
                return false;
            }
            return true;
        };
        System.out.println("Predicate接口test方法 " + predicate.test(person));
        Predicate<Person> negate = predicate.negate();
        System.out.println("Predicate接口negate方法 " + negate.test(person));
        Predicate<Person> or = predicate.or(p -> {
            if (person.getFirstName() == null) {
                return true;
            }
            return false;
        });
        System.out.println("Predicate接口or方法 " + or.test(person));

        // 2 Function 接口 函数型接口 有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）
        // a. R apply(T t) -> 将function对象应用到输入的参数上，并且输出结果
        Function<String, Integer> toInteger = Integer::valueOf;

        Function<String, Integer> toInteger1 = from -> Integer.valueOf(from);

        // System.out.println("Function接口apply方法 " + toInteger.apply("123"));
        // b. default <V> Function<T,V> andThen(Function<? super R, ? extends V> after) -> 先执行Function<T,
        // R>对象的apply()，再执行after的apply(),返回一个新的Function
        Function<String, Integer> andThen1111 = toInteger.andThen(num -> num + 10);
        System.out.println("Function接口andThen方法 " + andThen1111.apply("123"));
        // c.2. default <V> Function<V,R>compose(Function<? super V,? extends T> before)->
        // 先执行before的apply()，再执行Function<T,R>的apply(),返回一个新的Function
        Function<String, Integer> compose = toInteger.compose(num -> num + 10);
        System.out.println("Function接口compose方法 " + compose.apply("123"));

        // 3 Supplier接口 供给型接口 返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier<String> supplierStr = () -> {
            return "Supplier接口的字符串返回";
        };

        // 4 Consumer接口 消费型接口 表示一个接受单个输入参数并且没有返回值的操作。不像其他函数式接口，Consumer接口期望执行带有副作用的操作（译者注：Consumer的操作可能会更改输入参数的内部状态）
        List<String> strList = new ArrayList<String>() {
            {
                add("aaa");
                add("bbb");
                add("ccc");
                add("ddd");
            }
        };
        @SuppressWarnings("unchecked")
        Consumer<List<String>> consumerList = list -> {
            list.forEach(str -> {
                System.out.print(str + "  ");
            });

        };
        // consumerList.accept(strList);
        Consumer<List<String>> consumerAndThen = consumerList.andThen(list -> {
            list.forEach(str -> {
                System.out.print("andThen输出" + str + "  ");
            });
        });
        consumerAndThen.accept(strList);

        // 5 Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法
        Comparator<Person> comparatorFirstNameBase = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Comparator<Person> comparatorLastNameBase = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        System.out.println("\nComparator接口compare方法  " + comparatorFirstNameBase.compare(p1, p2)); // > 0
        // 取反
        System.out.println("Comparator接口reversed方法  " + comparatorFirstNameBase.reversed().compare(p1, p2)); // < 0

        // 联合排序，先按照firstName排序再按照lastName排序
        Comparator<Person> finalComparator = comparatorFirstNameBase.thenComparing(comparatorLastNameBase);
        finalComparator.compare(p1, p2);
    }
}
