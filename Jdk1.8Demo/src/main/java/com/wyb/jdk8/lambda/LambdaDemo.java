package com.wyb.jdk8.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * lambda表达式的基本格式语法
 */
public class LambdaDemo {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

            }
        };

        // lambda表达式的本质是一个函数式接口

        // 语法格式一：无参数，无返回值
        Runnable r2 = () -> System.out.println("hello lambda");
        r2.run();

        // 语法格式二：有一个参数，并且无返回值
        Consumer consumer1 = (x) -> System.out.print(x);

        // 语法格式三：若只有一个参数，小括号可以省略不写
        Consumer consumer2 = x -> System.out.print(x);

        // 语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
        Comparator<Integer> c1 = (x, y) -> {
            System.out.print(Integer.compare(x, y) + "函数式接口");
            return Integer.compare(x, y);
        };
        c1.compare(1, 2);

        // 语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
        Comparator<Integer> c2 = (x, y) -> Integer.compare(x, y);

        // 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，
        // 因为JVM编译器可以通过上下文进行类型推断出数据类型，既“类型推断”。
        Comparator<Integer> c3 = (Integer x, Integer y) -> Integer.compare(x, y);
    }

    public static boolean echo(List<Object> names) {
        return true;
    }

    interface GiveRaiseFun<F, T> {
        T raise(F from);

        T noParam();

        void noReturn(F from);
    }
}
