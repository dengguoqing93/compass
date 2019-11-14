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

import com.google.common.base.Predicate;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-13
 */
public class TestMethod<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMethod.class);

    private Class<T> testBeanClass;

    private String testMethodName;

    private Class[] paramTypes;

    private Class returnType;

    private Method testMethod;

    private Object targetBean;

    public Class<T> getTestBeanClass() {
        return testBeanClass;
    }

    public TestMethod<T> addTestBeanClass(Class<T> tClass) {
        this.testBeanClass = tClass;
        return this;
    }

    public TestMethod<T> addTestMethodName(String testMethodName) {
        this.testMethodName = testMethodName;
        return this;
    }

    public TestMethod<T> addParamTypes(Class... paramTypes) {
        this.paramTypes = paramTypes;
        return this;
    }

    public TestMethod<T> addReturnType(Class returnType) {
        this.returnType = returnType;
        return this;
    }

    public static <T> TestMethod<T> builder() {
        return new TestMethod();
    }

    public TestMethod build() {

        afterPropertiesSet();

        Set<Method> methods = getMethod();

        int size = methods.size();

        if (size == 0 ) {
            throw new IllegalArgumentException("Not found target method.");
        } else if (size > 1) {
            throw new IllegalArgumentException("Target method size > 1");
        }

        this.testMethod = methods.stream().findFirst().get();

        return this;

    }

    public void addTargetBean(Object targetBean) {
        this.targetBean = targetBean;
    }

    private void afterPropertiesSet() {

        if (this.testBeanClass == null) {
            throw new IllegalArgumentException("testBeanClass found was null");
        }

        if (this.testMethodName == null || "".equals(this.testMethodName)) {
            throw new IllegalArgumentException("testMethodName found was null");
        }
    }

    private Set<Method> getMethod() {

        List<Predicate> predicates = new ArrayList<>(10);

        predicates.add(ReflectionUtils.withName(testMethodName));

        if (this.paramTypes != null) {
            predicates.add(ReflectionUtils.withParameters(paramTypes));
        }

        if (this.returnType != null) {
            predicates.add(ReflectionUtils.withReturnType(returnType));
        }else {
            predicates.add(ReflectionUtils.withReturnType(void.class));
        }

        return ReflectionUtils.getMethods(testBeanClass, predicates.toArray(new Predicate[predicates.size()]));

    }

    public Object call(Object... objects) throws Exception {

        try {
            return this.testMethod.invoke(targetBean, objects);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new Exception(e.getTargetException());
        }
        return null;
    }

}
