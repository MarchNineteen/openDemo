package com.wyb.jdk8;

import com.wyb.jdk8.lambda.Person;

import java.util.Optional;

/**
 * （七）jdk1.8提供的新类--optional 是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 */
public class JDK8OptionalClassDemo {
    public static void main(String[] args) {
        Person optionalValue = new Person();
        // 1 of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。如果传入参数为null，则抛出NullPointerException 。
        Optional<Person> optional = Optional.of(optionalValue);
        System.out.println(optional.isPresent());

        // 为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
        // 2 ofNullable与of方法相似，唯一的区别是可以接受参数为null的情况。
        Optional<Person> empty = Optional.ofNullable(null);

        // 3 isPresent方法，如果值存在返回true，否则返回false。
        System.out.println(empty.isPresent());

        // 4 get方法，如果Optional有值则将其返回，否则抛出NoSuchElementException。
        System.out.println(optional.get());
        // System.out.println(empty.get());

        // 5 ifPresent方法，如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent(p -> {
            System.out.println("pserson的firstName：" + p.getFirstName());
        });

        // 6 orElse方法，如果有值则将其返回，否则返回指定的其它值。
        System.out.println("optional对象的orElse方法：" + optional.orElse(new Person("John", "Doe")));
        System.out.println("empty对象的orElse方法：" + empty.orElse(new Person("John", "Doe")));

        // 7 orElseGet方法，orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的参数作为默认值，orElseGet方法可以接受Supplier接口的实现用来生成默认值。
        System.out.println("optional对象的orElse方法：" + optional.orElseGet(() -> {
            return new Person("Alice", "Wonderland");
        }));
        System.out.println("empty对象的orElseGet方法：" + empty.orElseGet(() -> {
            return new Person("Alice", "Wonderland");
        }));

        // 8 orElseThrow方法，如果有值则将其返回，否则抛出supplier接口创建的异常。
        try {
            optional.orElseThrow(ValueAbsentException::new);
        }
        catch (ValueAbsentException e) {
            System.out.println("optional对象的orElseThrow方法异常：" + e.getMessage());
        }
        try {
            empty.orElseThrow(ValueAbsentException::new);
        }
        catch (ValueAbsentException e) {
            System.out.println("empty对象的orElseThrow方法异常：" + e.getMessage());
        }

        // 9 map方法，如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
        Optional<Person> optionalMap = optional.map(p -> {
            p.setFirstName("Anne");
            return p;
        });
        System.out.println("optional对象的map方法：" + optionalMap.get().getFirstName());

        // 10 flatMap方法，如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。
        // flatMap与map（Funtion）方法类似，区别在于flatMap中的mapper返回值必须是Optional。调用结束时，flatMap不会对结果用Optional封装。
        Optional<Person> optionalFlatMap = optional.flatMap(p -> {
            p.setFirstName("Harry");
            Optional<Person> o = Optional.of(p);
            return o;
        });
        System.out.println("optional对象的flatMap方法：" + optionalFlatMap.get().getFirstName());

        // 11 filter方法，如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional
        Optional<Person> optionalFilter = optionalMap.filter(p -> {
            return p.getFirstName() != null;
        });
        System.out.println("optionalMap对象的filter方法：" + optionalFilter.get().getFirstName());
    }
}

class ValueAbsentException extends Throwable {
    private static final long serialVersionUID = 1L;

    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "No value present in the Optional instance";
    }
}
