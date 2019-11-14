/*
 * Copyright 2019 The  Project
 *
 * The   Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.eleven.examples.service.impl;

import org.eleven.compass.domain.AbstractCompassTestSuit;
import org.eleven.examples.dao.DiscountDao;
import org.eleven.examples.entity.Discount;
import org.eleven.examples.exception.PhoneException;
import org.eleven.examples.service.DiscountService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-14
 */
public class DiscountServiceImplTest2 extends AbstractCompassTestSuit<DiscountService,BigDecimal, PhoneException> {

    @Autowired
    private DiscountDao discountDao;


    @Override
    protected void loadTestPlan() {
        testPlan.addTestCase(testCase("测试修改折扣率小于0")
                .addInputArgs("sdk0000001", BigDecimal.valueOf(-1.0))
                .thenException(e -> "error001".equals(e.getErrCode()))
        ).addTestCase(testCase("测试修改折扣率大于100")
                .addInputArgs("sdk9999999", BigDecimal.valueOf(101.0))
                .thenException(e -> "error002".equals(e.getErrCode()))
        ).addTestCase(testCase("测试折扣不存在")
                        .addInputArgs("sdk9999999", BigDecimal.valueOf(1.0))
                        .thenException(e -> "error003".equals(e.getErrCode()))
        ).addTestCase(testCase("测试修改折扣值符合预期")
                    .addDataUp(createDataUp().build().addDataUpMethod(
                            createTestMethod().<DiscountDao>build()
                            .addTestBeanClass(DiscountDao.class)
                            .addTestMethodName("insert")
                            .addParamTypes(Discount.class).build())
                            .addData(new Discount("sdk0000001", new Date(), new Date(), BigDecimal.valueOf(0.17))))
                .addInputArgs("sdk0000001",0.5).thenComplete(() -> {
                    BigDecimal dbDiscount = discountDao.getDiscountBySdk("sdk0000001");
                    Assert.assertFalse("修改值正确",
                            BigDecimal.valueOf(0.5).compareTo(dbDiscount) != 0 ? false : true);
                })
        );
    }

    @Override
    protected void loadTestMethod() {
        testMethod.addTestBeanClass(DiscountService.class);
        testMethod.addTestMethodName("modifyDiscountBySDK");
        testMethod.addParamTypes(String.class, BigDecimal.class);
        testMethod.addReturnType(void.class);
    }
}
