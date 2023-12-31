package edu.jmu.lrui1.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报纸表
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Newspaper implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 国内统一刊号
     */
    private String newspaperId;

    /**
     * 名称
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 版面规格
     */
    private String pageSize;

    /**
     * 出版社编号
     */
    private String pressId;


}
