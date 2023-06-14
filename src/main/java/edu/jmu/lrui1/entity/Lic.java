package edu.jmu.lrui1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 许可
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Lic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 许可id
     */
    @TableId(value = "lic_id", type = IdType.AUTO)
    private Long licId;

    /**
     * 名称
     */
    private String name;

    /**
     * 许可code
     */
    private String lic_code;

    /**
     * 描述
     */
    private String desc;


}
