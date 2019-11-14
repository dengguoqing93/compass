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
package org.eleven.compass.domain;

import org.eleven.compass.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.eleven.compass.TestCase.builder;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public abstract class AbstractCompassTestSuit<T, O, E> {

    @Autowired
    private ApplicationContext applicationContext;

    protected TestMethod<T> testMethod = new TestMethod<>();

    protected boolean byType = true;

    protected TestPlan<O, E> testPlan = new TestPlan<>();


    @Before
    public void prepareContext() {
        loadTestMethod();
        setTestBean();
        loadTestPlan();
        prepareTestPlan();

    }

    private void prepareTestPlan() {
        if (testPlan.getGlobalDataUps() != null && testPlan.getGlobalDataUps().size() > 0) {

            testPlan.getGlobalDataUps().forEach(testDataUp -> {
                initDataUp(testDataUp);
                testDataUp.run();
            });
        }

        prepareTestCase();
    }

    public TestCase<O, E> testCase(String caseName) {
        return TestCase.builder(caseName);
    }

    public TestDataUpBuilder createDataUp() {
        return new TestDataUpBuilder();
    }

    public TestMethodBuilder createTestMethod() {
        return new TestMethodBuilder();
    }

    private void prepareTestCase() {
        if (testPlan.getTestCases() != null && testPlan.getTestCases().size() > 0) {
            testPlan.getTestCases().forEach(testCase -> {
                testCase.check();
                testCase.getDataUps().forEach(testDataUp -> initDataUp(testDataUp));
            });
        }
    }

    private void initDataUp(TestDataUp testDataUp) {
        TestMethod method = testDataUp.getDataUpMethod();
        method.addTargetBean(applicationContext.getBean(method.getTestBeanClass()));

    }

    private void setTestBean() {
        testMethod.build();
        testMethod.addTargetBean(getBean());
    }

    private Object getBean() {
        if (byType) {
            return applicationContext.getBean(testMethod.getTestBeanClass());
        } else {
            return applicationContext.getBean("");
        }
    }

    /**
     * Get test case
     *
     * @return
     */
    protected abstract void loadTestPlan();

    /**
     * load text context
     */
    protected abstract void loadTestMethod();

    @Test
    public void execTestCases() {


        testPlan.getTestCases().forEach(testCase -> testCase.run(testMethod));

    }


}
