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
package org.eleven.examples.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-12
 */
public class Phone implements Serializable {

    private static final long serialVersionUID = -8446319967823518965L;

    private Long id;

    private String sdk;

    private String brand;

    private String model;

    private Integer memory;

    private String color;

    private Integer weight;

    private String pattern;

    private BigDecimal price;

    private String origin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public Phone() {
    }

    public Phone(String brand, String model, Integer memory, String color, String origin) {
        this.brand = brand;
        this.model = model;
        this.memory = memory;
        this.color = color;
        this.origin = origin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", sdk='" + sdk + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", memory=" + memory +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", pattern='" + pattern + '\'' +
                ", price=" + price +
                ", origin='" + origin + '\'' +
                '}';
    }
}
