package live.nobug.day01.reflect;

import live.nobug.day01.domain.Person;

/**
 * 同一个字节码文件，在一次程序运行过程中，只会被加载一次，不论通过哪种方式加载，获取的都是同一个Class对象
 */
public class ReflectDemo01 {
    public static void main(String[] args) throws Exception {
        // 多用于配置文件，将类名定义在配置文件中，读取文件加载类
        Class cls1 = Class.forName("live.nobug.day01.domain.Person");
        System.out.println(cls1);

        // 多用于参数的传递
        Class cls2 = Person.class;
        System.out.println(cls2);

        // 多用于对象获取字节码对象
        Person person = new Person();
        Class cls3 = person.getClass();
        System.out.println(cls3);

        System.out.println(cls1 == cls2);// true
        System.out.println(cls2 == cls3);// true
    }
}
