package com.wyb.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Lambda表达式在list中的使用
 */
public class LambdaForListDemo {

    public static void main(String[] args) {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        // 消费型接口(只输入，无返回值)
        System.out.println("\n 消费型接口，计算并打印所有程序员的原薪水和现薪水:");
        Consumer<Person> giveRaise = e -> {
            e.setNewSalary(e.getSalary() / 100 * 5 + e.getSalary());
            System.out.println(e.getSalary() + "  " + e.getNewSalary());
        };
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        System.out.println("\n 功能性接口，计算并打印所有程序员的原薪水和现薪水:");
        // 功能性接口(有输入，有返回值)
        GiveRaiseFun<Person, Integer> giveRaiseFun = e1 -> e1.getSalary() / 100 * 5 + e1.getSalary();
        javaProgrammers.forEach(e -> {
            e.setNewSalary(giveRaiseFun.raise(e));
            System.out.println(e.getSalary() + "  " + e.getNewSalary());
        });

        System.out.println("\n 功能性接口，lambda表达式为代码块时，计算并打印所有程序员的原薪水和现薪水:");
        // 功能性接口(有输入，有返回值) lambda表达式为代码块时
        GiveRaiseFun<Person, Integer> giveRaiseFun2 = e1 -> {
            if (e1.getSalary() > 10000) {
                return e1.getSalary() / 100 * 5 + e1.getSalary();
            }
            return e1.getSalary() / 100 * 10 + e1.getSalary();
        };
        javaProgrammers.forEach(e -> {
            e.setNewSalary(giveRaiseFun2.raise(e));
            System.out.println(e.getSalary() + "  " + e.getNewSalary());
        });

        // 关于Lambda的作用域
        List<Person> result = new ArrayList<Person>();
        Person person = new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800);
        int num = 2;
        Integer numInteger = 2;
        // 过滤器，筛选出符合条件的元素
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream().filter(p -> p.getSalary() > 1400).forEach(p -> {
            result.add(p);
            person.setAge(num + numInteger);
            // num = 3;
            // numInteger = 3;
            System.out.printf("%s %s; ", p.getFirstName(), p.getLastName());
        });
        System.out.print("\n result: ");
        result.forEach(r -> System.out.print(r.getFirstName() + "  "));
        System.out.println("\n person.age:" + person.getAge());
    }
}

@FunctionalInterface
interface GiveRaiseFun<F, T> {
    T raise(F from);

    default String TestDefaultFun(String param) {
        return "";
    }

    static String TestStaticFun(String param) {
        return "";
    }
}
