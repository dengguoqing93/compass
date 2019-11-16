package org.eleven.paramter.domain;

/**
 * 参数属性定义
 *
 * @author dengguoqing
 * @date 2019/11/16
 */
public class ParamterPropeties<T> {

    /**
     * 唯一标识
     */
    private String uniqueId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 参数类型
     */
    private T t;

    /**
     * 属性最大值
     */
    private T max;

    /**
     * 属性最小值
     */
    private T min;

    /**
     * 属性值是否允许为空
     */
    private Boolean isNull;

    /**
     * 更新属性名称
     *
     * @param name 更新后的名称
     */
    public ParamterPropeties modifyName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 更新是否为空的属性
     *
     * @param isNull 是否允许为空
     * @return org.eleven.paramter.domain.ParamterPropeties
     * @author dengguoqing
     * @date 2019/11/16
     * @since 1.0
     */
    public ParamterPropeties modifyIsNull(Boolean isNull) {

        this.isNull = isNull;
        return this;
    }
}
