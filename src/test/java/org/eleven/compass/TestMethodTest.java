package org.eleven.compass;


import org.eleven.compass.test.BizService;
import org.junit.Test;

public class TestMethodTest {

    @Test
    public void build1() {

        TestMethod<BizService> testMethod = new TestMethod<>();
        testMethod.addTestBeanClass(org.eleven.compass.test.BizService.class);
        testMethod.addTestMethodName("testMethod1");
        testMethod.addParamTypes(String.class, int.class);
        testMethod.addReturnType(String.class);


        testMethod.build();

    }

    @Test
    public void build2() {

        TestMethod<BizService> testMethod = new TestMethod<>();
        testMethod.addTestBeanClass(BizService.class);
        testMethod.addTestMethodName("testMethod2");
        testMethod.addParamTypes(String.class, int.class);
        testMethod.addReturnType(void.class);

        testMethod.build();

    }

    @Test
    public void build3() {

        TestMethod<BizService> testMethod = new TestMethod<>();
        testMethod.addTestBeanClass(BizService.class);
        testMethod.addTestMethodName("testMethod3");
        testMethod.addReturnType(void.class);

        testMethod.build();

    }
}