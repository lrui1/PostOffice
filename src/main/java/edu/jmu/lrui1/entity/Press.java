package edu.jmu.lrui1.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 出版社
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Press implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出版社编号
     */
    private String press_id;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 联系地址
     */
    private String address;


}
