package com.wyb.jdk8.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * 函数式接口（也叫功能性接口，函数式接口是只包含一个方法的接口。比如Java标准库中的java.lang.Runnable和 java.util.Comparator都是典型的函数式接口）
 *
 */
@FunctionalInterface
public interface TestFunctionalInterface {

    public String normalFunction1FromFunctionalInterface();

    /**
     * 函数式接口的静态方法1
     *
     * @return
     */
    public static String staticFunction1FromFunctionalInterface() {
        return "";
    }

    /**
     * 函数式接口的静态方法2
     *
     * @return
     */
    public static String staticFunction2FromFunctionalInterface() {
        return "";
    }

    /**
     * 函数式接口的默认方法
     *
     * @param str
     * @return
     */
    default List<String> defaultFunctionFromFunctionalInterface(String str) {
        List<String> list = new ArrayList<String>();
        if (str == null) {
            list.add("");
            return list;
        }
        else {
            list.add(str);
            return list;
        }
    }

    /**
     * 函数式接口的默认方法
     *
     * @param str
     * @return
     */
    default List<String> defaultFunctionFromFunctionalInterface2(String str) {
        List<String> list = new ArrayList<String>();
        if (str == null) {
            list.add("");
            return list;
        }
        else {
            list.add(str);
            return list;
        }
    }
}
