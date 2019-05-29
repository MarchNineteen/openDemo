package com.wyb.jdk8;

import com.wyb.jdk8.lambda.Person;

/**
 * （四）方法与构造函数引用 Java 8 允许使用 :: 关键字来传递方法或者构造函数引用
 */
public class FunctionAndConstructionQuoteDemo {
    public static void main(String[] args) {
        // lambda表达式的本质是一个函数式接口
        Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
        Integer converted1 = converter1.convert("123");
        System.out.println(converted1);// 123

        // 函数式接口可被:: 关键字来传递方法，则接口Converter的convert方法等价于Integer的valueOf方法
        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer converted2 = converter2.convert("123");
        System.out.println(converted2); // 123

        // 使用::关键字传递时，参数个数、类型返回类型，源方法要和函数式接口的方法定义的一致
        // a 使用类名称::方法名形式传递静态方法
        ConverterWithParams<Integer, Boolean, String> converter3 = TestClass::caculator;
        String converted3 = converter3.convert(1, false);
        System.out.println(converted3);

        // b 使用类实例化对象::方法名形式传递非静态方法
        TestClass testClass = new TestClass();
        ConverterWithParams<Integer, Boolean, String> converter4 = testClass::caculator2;
        String converted4 = converter4.convert(6, true);
        System.out.println(converted4);

        // 使用::关键字传递构造器
        PersonFactory<Person> personFactory = Person::new;
        Person person1 = personFactory.create("Peter", "Parker");
        // 返回必定是一个函数式接口，则只允许有一个抽象方法
        // Person person2 = personFactory.create("Jarrod", "Pace", "PHP programmer", "male", 34, 1550);
        System.out
                .println("person.firstName: " + person1.getFirstName() + ", person.lastName: " + person1.getLastName());
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

@FunctionalInterface
interface ConverterWithParams<F, P, T> {
    T convert(F from, P param);
}

class TestClass {
    public static String caculator(int day, boolean isStudent) {
        StringBuffer str = new StringBuffer();
        if (!isStudent) {
            str.append("不是学生，已经不用上学啦");
            return str.toString();
        }
        if (day == 6 || day == 7) {
            str.append("今天周末，不用上学");
        } else {
            str.append("今天要上学  T_T");
        }
        return str.toString();
    }

    public String caculator2(int day, boolean isStudent) {
        StringBuffer str = new StringBuffer();
        if (!isStudent) {
            str.append("【非静态方法】不是学生，已经不用上学啦");
            return str.toString();
        }
        if (day == 6 || day == 7) {
            str.append("【非静态方法】今天周末，不用上学");
        } else {
            str.append("【非静态方法】今天要上学  T_T");
        }
        return str.toString();
    }
}

// 一个接口，创建Person对象的对象工厂接口
interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);

    // P create(String firstName, String lastName, String job, String gender, int age, int salary);
}
