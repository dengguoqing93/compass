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
package org.eleven.compass;

import org.eleven.compass.handler.CheckDataAfterRunHandler;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-12
 */
public class TestCase<O,E> {

    private String caseName;

    private List<TestDataUp> dataUps = new ArrayList<>(10);

    private Object[] inputs;

    private Predicate<O> checkReturnHandler;

    private Predicate<E> checkExceptionHandler;

    private CheckDataAfterRunHandler checkDataAfterRunHandler;

    private static final String MESSAGE_FORMAT = "单元测试用例[%s]执行[%s]验证失败！";

    public static <O,E> TestCase<O,E> builder(String caseName) {
        return new TestCase(caseName);
    }

    public TestCase(String caseName) {
        this.caseName = caseName;
    }

    public TestCase<O, E> addDataUp(TestDataUp testDataUp) {
        this.dataUps.add(testDataUp);
        return this;
    }

    public List<TestDataUp> getDataUps() {
        return dataUps;
    }

    public TestCase<O,E> addInputArgs(Object... objects) {
        inputs = objects;
        return this;
    }

    public TestCase<O,E> thenException(Predicate<E> predicate) {
        this.checkExceptionHandler = predicate;
        return this;
    }

    public TestCase<O,E> thenSuccess(Predicate<O> predicate) {
        this.checkReturnHandler = predicate;
        return this;
    }

    public TestCase<O, E> thenComplete(CheckDataAfterRunHandler handler) {

        this.checkDataAfterRunHandler = handler;
        return this;

    }

    public void check() {
        if (checkReturnHandler == null && checkDataAfterRunHandler == null && checkExceptionHandler == null) {
            throw new IllegalArgumentException(String.format("单元测试用例[%s]配置错误，不存在任何验证用例的Handler！", caseName));
        }
    }

    public void run(TestMethod method) {

        if (this.getDataUps() != null || this.getDataUps().size() > 0) {
            this.getDataUps().forEach(testDataUp -> testDataUp.run());
        }

        Object result = null;

        try {
            result = method.call(inputs);
        }  catch (Exception e) {
            if (this.checkExceptionHandler != null) {

                checkException((E) e.getCause());
            }
        }

        if (this.checkReturnHandler != null) {
            checkReturn(result);
        }

        if (this.checkDataAfterRunHandler != null) {
            checkDataAfterRun();
        }


    }

    private void checkReturn(Object result) {
        Assert.assertTrue(String.format(MESSAGE_FORMAT,this.caseName,"方法返回结果"), checkReturnHandler.test((O) result));
    }

    private void checkException(E e) {
        Assert.assertFalse(String.format(MESSAGE_FORMAT,this.caseName,"方法抛出异常"), checkExceptionHandler.test(e));
    }

    private void checkDataAfterRun() {
        checkDataAfterRunHandler.check();

    }
}
