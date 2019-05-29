package com.wyb.jdk8.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface TestInterface2 {

    /**
     * 一般接口2的普通抽象方法1
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public List<String> normalFunction1FromInterface2(String arg1, String arg2);

    /**
     * 一般接口2的普通抽象方法2
     *
     * @param arg1
     * @param arg2
     * @return
     */
    public List<String> normalFunction2FromInterface2(String arg1, String arg2);

    /**
     * 一般接口2的默认方法
     *
     * @param str
     * @return
     */
    default List<String> defaultFunctionFromInterface2(String str) {
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
