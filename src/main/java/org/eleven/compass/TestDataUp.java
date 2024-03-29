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

import java.util.ArrayList;
import java.util.List;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-13
 */
public class TestDataUp<T> {

    private TestMethod dataUpMethod;

    private List data = new ArrayList<>(10);

    public TestDataUp addDataUpMethod(TestMethod testMethod) {
        this.dataUpMethod = testMethod;
        return this;
    }

    public <T> TestDataUp<T> addData(T object) {

        data.add(object);
        return (TestDataUp<T>) this;
    }

    public TestMethod getDataUpMethod() {
        return dataUpMethod;
    }

    public List<T> getData() {
        return data;
    }

    public void run() {

        dataUpMethod.build();

        data.forEach(o -> {
            try {
                dataUpMethod.call(o);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }
}
