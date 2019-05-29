package com.wyb.jdk8.interfaces;

/**
 * 一般接口，允许出现一个default开头的非抽象类方法，支持static静态方法，可被子类继承
 */
public interface TestInterface1 {

    /**
     * 一般接口1的静态方法1
     *
     * @return
     */
    public static String staticFunction1FromInterface1() {
        return "";
    }

    /**
     * 一般接口1的静态方法2
     *
     * @return
     */
    public static String staticFunction2FromInterface1() {
        return "";
    }

    /**
     * 一般接口1的普通抽象方法1
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public String normalFunction1FromInterface1(String arg1, String arg2);

    /**
     * 一般接口1的普通抽象方法2
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public String normalFunction2FromInterface1(String arg1, String arg2);

    /**
     * 一般接口1的默认方法
     *
     * @param str
     * @return
     */
    default String defaultFunctionFromInterface1(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }
}
