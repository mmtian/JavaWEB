package live.nobug.day01.test;

import live.nobug.day01.junit.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    /**
     * 初始化方法：
     * 用于资源申请，所有测试方法执行之前都会执行此方法
     */
    @Before
    public void init() {
        System.out.println("init...");
    }

    /**
     * 测试add方法
     */
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(10, 20);
        System.out.println("testAdd...");

        Assert.assertEquals(30, result);

    }

    @Test
    public void testSub() {
        Calculator calculator = new Calculator();
        int result = calculator.sub(1, 2);

        Assert.assertEquals(-1, result);
    }

    /**
     * 结束方法：
     *  用于释放资源，所有测试方法执行之后都会执行该方法
     */
    @After
    public void close(){
        System.out.println("close...");
    }

}
