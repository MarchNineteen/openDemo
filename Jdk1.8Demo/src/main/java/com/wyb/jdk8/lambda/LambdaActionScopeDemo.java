package com.wyb.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * （五）lambda表达式的作用域
 */
public class LambdaActionScopeDemo {
    public static String staticStr = "old word";
    public static int staticNum = 1;
    public static List<Person> staticList = new ArrayList<Person>();

    public static void main(String[] args) {
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

        // 关于Lambda的作用域
        // 1 对局部变量的访问：a 可以访问外层的局部变量 b 此局部变量可以不用final关键字修饰 c 此局部变量不能被重新赋值，否则编译报错（事实上的final变量）d 在 Lambda
        // 表达式当中不允许声明一个与局部变量同名的参数或者局部变量
        int number = 1;
        Converter<Integer, String> s = (param) -> {
            return String.valueOf(param + number);
        };
        // number = 5;

        // 2 对对象的访问:a 可读可写，即可以修改属性、元素，但是不能重新赋值
        List<Person> result = new ArrayList<Person>();
        Person person = new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800);
        int num = 2;
        Integer numInteger = 2;
        // 过滤器，筛选出符合条件的元素
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream().filter(p -> p.getSalary() > 1400).forEach(p -> {
            result.add(p);
            person.setAge(num + numInteger);
            // result = new ArrayList<Person>();
            // person = new Person();
            // num = 3;
            // numInteger = 3;
            System.out.printf("%s %s; ", p.getFirstName(), p.getLastName());
        });
        System.out.print("\n result: ");
        result.forEach(r -> System.out.print(r.getFirstName() + "  "));
        System.out.println("\n person.age:" + person.getAge());

        // 3 对静态变量的访问 a 可读可写，能重新赋值
        Converter<Integer, String> s1 = (param) -> {
            staticStr = "new word";
            staticNum = 2;
            staticList = new ArrayList<Person>();
            System.out.println("内部输出  staticStr: " + staticStr + ",staticNum: " + staticNum);
            return String.valueOf(param + number);
        };
        System.out.println("3.对静态变量的访问    " + s1.convert(2));
        System.out.println("外部输出  staticStr: " + staticStr + ",staticNum: " + staticNum);

        // 4 对接口默认方法的访问 a 访问不到接口的默认方法
        Converter<Integer, String> s2 = (param) -> {
            // converterInterfaceDefaultFunction();
            return String.valueOf(param + number);
        };
        Converter<Integer, String> s3 = new Converter<Integer, String>() {
            @Override
            public String convert(Integer from) {
                converterInterfaceDefaultFunction();
                return null;
            }
        };

        // 5 this的指向
        LambdaActionScopeDemo lambdaActionScopeDemo = new LambdaActionScopeDemo();
        lambdaActionScopeDemo.method();
    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);

        default String converterInterfaceDefaultFunction() {
            return "函数式接口默认方法";
        }

        public static String converterInterfaceStaticFunction() {
            return "函数式接口静态方法";
        }
    }

    public String outSidePublicFunction() {
        return "外部public类";
    }

    public void method() {
        System.out.println(this.toString());
        Runnable runnable = () -> {
            outSidePublicFunction();
            // Lambda 表达式中使用 this 会引用创建该 Lambda 表达式的方法的 this 参数
            System.out.println(this.toString());
        };
        new Thread(runnable).start();
    }

    @Override
    public String toString() {
        return "this的指向  Lambda";
    }
}
