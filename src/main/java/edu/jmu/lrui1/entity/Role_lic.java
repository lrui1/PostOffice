package edu.jmu.lrui1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色许可关系
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role_lic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "role_lic_id", type = IdType.AUTO)
    private Long role_lic_id;

    /**
     * 角色id
     */
    private Long role_id;

    /**
     * 许可id
     */
    private Long lic_id;


}
