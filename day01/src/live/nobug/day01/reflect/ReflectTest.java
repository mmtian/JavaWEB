package live.nobug.day01.reflect;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 框架类
 * 需求：
 * 可以生成任意对象，并运行任意方法
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
//        String fileName = "day01\\pro.properties";
//        FileReader reader = new FileReader(fileName);
//        properties.load(reader);

        // 加载配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        properties.load(is);

        // 类名
        String className = properties.getProperty("className");
        // 方法名
        String methodName = properties.getProperty("methodName");

        Class<?> cls = Class.forName(className);

        Constructor constructor = cls.getDeclaredConstructor();
        Object obj = constructor.newInstance();

        Method method = cls.getDeclaredMethod(methodName);
        method.invoke(obj);
    }
}
