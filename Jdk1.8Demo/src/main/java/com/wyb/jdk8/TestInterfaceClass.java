package com.wyb.jdk8;

import com.wyb.jdk8.interfaces.TestFunctionalInterface;
import com.wyb.jdk8.interfaces.TestInterface1;
import com.wyb.jdk8.interfaces.TestInterface2;

import java.util.List;


/**
 * （三）函数式接口</br>
 * 实现三个接口，同时继承这三个接口的非抽象类方法
 */
public class TestInterfaceClass implements TestInterface1, TestInterface2, TestFunctionalInterface {

    public static void main(String[] args) {
        // 直接用接口名调用接口的静态方法
        TestFunctionalInterface.staticFunction1FromFunctionalInterface();

        // 用实现类实例调用接口的默认方法
        TestInterfaceClass testInterfaceClass = new TestInterfaceClass();
        testInterfaceClass.defaultFunctionFromFunctionalInterface("");
    }

    @Override
    public List<String> normalFunction1FromInterface2(String arg1, String arg2) {
        // 继承自TestInterface2的接口实现
        String str = defaultFunctionFromInterface1(arg1);
        List<String> list = defaultFunctionFromInterface2(arg2);
        return null;
    }

    @Override
    public List<String> normalFunction2FromInterface2(String arg1, String arg2) {
        // 继承自TestInterface2的接口实现
        String str = defaultFunctionFromInterface1(arg1);
        List<String> list = defaultFunctionFromInterface2(arg2);
        return null;
    }

    @Override
    public String normalFunction1FromInterface1(String arg1, String arg2) {
        // 继承自TestInterface1的接口实现
        return null;
    }

    @Override
    public String normalFunction2FromInterface1(String arg1, String arg2) {
        // 继承自TestInterface1的接口实现
        return null;
    }

    @Override
    public String normalFunction1FromFunctionalInterface() {
        // 继承自函数式接口TestFunctionalInterface的实现
        List<String> list = defaultFunctionFromFunctionalInterface("");
        return null;
    }

}
