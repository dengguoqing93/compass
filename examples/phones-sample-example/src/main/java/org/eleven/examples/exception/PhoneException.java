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
package org.eleven.examples.exception;

/**
 * @author: <a herf="mailto:jarodchao@126.com>jarod </a>
 * @date: 2019-11-12
 */
public class PhoneException extends RuntimeException {

    private String errCode;

    private String errMesg;

    private static final String MESSAGE_FORMAT = "异常信息: errCode[%s] errMsg[%s]";

    public PhoneException(String errCode,String errMsg) {
        super(String.format(MESSAGE_FORMAT, errCode, errMsg));
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMesg() {
        return errMesg;
    }
}
