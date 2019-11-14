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
package org.eleven.examples.dao;

import org.apache.ibatis.annotations.*;
import org.eleven.examples.entity.Discount;

import java.math.BigDecimal;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-12
 */
@Mapper
public interface DiscountDao {

    @Select(" select discount from t_discount where sdk = #{sdk,jdbcType=VARCHAR}")
    BigDecimal getDiscountBySdk(@Param("sdk") String sdk);

    @Insert("insert into t_discount(sdk, startDate, endDate, discount) " +
            "values(#{sdk,jdbcType=VARCHAR}," +
            "#{startDate,jdbcType=DATE}," +
            "#{endDate,jdbcType=DATE}," +
            "#{discount,jdbcType=DECIMAL}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Discount discount);

    @Update(" update t_discount " +
            " set discount = #{discount,jdbcType=DECIMAL} " +
            " where sdk = #{sdk,jdbcType=VARCHAR} ")
    void updateDiscountBySdk(@Param("sdk") String sdk, @Param("discount") BigDecimal discount);
}
