package org.eleven.examples.service.impl;

import org.eleven.compass.*;
import org.eleven.examples.dao.DiscountDao;
import org.eleven.examples.entity.Discount;
import org.eleven.examples.exception.PhoneException;
import org.eleven.examples.service.DiscountService;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountServiceImplTest1 extends AbstractCompassTestSuit<DiscountService> {

    @Override
    protected void loadTestPlan() {

        testPlan.addGlobalDataUp(TestDataUp.builder().addDataUpMethod(
                TestMethod.<DiscountDao>builder()
                        .addTestBeanClass(DiscountDao.class)
                        .addTestMethodName("insert")
                        .addParamTypes(Discount.class).build())
                .addData(new Discount("sdk0000001", new Date(), new Date(), BigDecimal.valueOf(0.17)))
        ).addTestCase(TestCase.<BigDecimal, PhoneException>builder("测试获取折扣返回值符合预期")
                .addInputArgs("sdk0000001")
                .thenSuccess(o -> o.equals(BigDecimal.valueOf(0.17)))
        ).addTestCase(TestCase.<BigDecimal, PhoneException>builder("测试获取数据库中不存在折扣时的返回值")
                .addInputArgs("sdk9999999")
                .thenSuccess(o -> o.equals(BigDecimal.ZERO)));
    }

    @Override
    protected void loadTestMethod() {
        testMethod.addTestBeanClass(DiscountService.class);
        testMethod.addTestMethodName("getDiscount");
        testMethod.addParamTypes(String.class);
        testMethod.addReturnType(BigDecimal.class);
    }



}