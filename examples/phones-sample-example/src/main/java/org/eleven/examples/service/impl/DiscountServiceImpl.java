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

import org.eleven.examples.dao.DiscountDao;
import org.eleven.examples.exception.PhoneException;
import org.eleven.examples.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-12
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDao discountDao;

    @Override
    public BigDecimal getDiscount(String sdk) throws PhoneException {

        BigDecimal discount = discountDao.getDiscountBySdk(sdk);

        return discount == null ? BigDecimal.ZERO : discount;
    }

    @Override
    public void modifyDiscountBySDK(String sdk, BigDecimal newDiscount) throws PhoneException {


        if (newDiscount.compareTo(BigDecimal.ZERO) < 0) {
            throw new PhoneException("error001", "折扣不能小于0.");
        }

        if (newDiscount.compareTo(BigDecimal.TEN.multiply(BigDecimal.TEN)) > 0) {
            throw new PhoneException("error002", "折扣不能小于100.");
        }

        BigDecimal discount = discountDao.getDiscountBySdk(sdk);

        if (discount == null) {
            throw new PhoneException("error003", "折扣信息不存在");
        }

        discountDao.updateDiscountBySdk(sdk, newDiscount);

    }
}
