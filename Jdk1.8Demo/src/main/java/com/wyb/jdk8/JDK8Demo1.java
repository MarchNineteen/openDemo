package com.wyb.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JDK8Demo1 {
    public static boolean echo(List<String> names, boolean hasStop) {
        int size = names.size();
        List<String> newNames = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String n = names.get(i > size - 1 ? size - 1 : i);
            newNames.add(n);
        }
        names = newNames;
        System.out.println(Arrays.toString(names.toArray(new String[0])));
        hasStop = true;
        return hasStop;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        // System.out.println(Arrays.toString(names.toArray(new String[0])));

        System.out.println("----------------");

        // 方法体
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(2 + "hello world");
            }
        }).start();

        boolean hasStop = false;
        new Thread(() -> echo(names, hasStop)).start();
        new Thread(() -> {
            while (!hasStop) {
                System.out.println("结束了吗？还没有");
                Thread t = Thread.currentThread();
                try {
                    t.sleep(1000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("看看数组值有没有改变：" + Arrays.toString(names.toArray(new String[0])));
            }
        }).start();
        // names.forEach(n -> System.out.println(n));
    }
}
