package edu.jmu.lrui1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private Long clientId;

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

    /**
     * 邮政编码
     */
    private String Postal_Code;


}
