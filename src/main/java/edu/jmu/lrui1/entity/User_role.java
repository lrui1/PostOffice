package edu.jmu.lrui1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色关系
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User_role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "user_role_id", type = IdType.AUTO)
    private Long user_role_id;

    /**
     * 用户id
     */
    private Long user_id;

    /**
     * 角色id
     */
    private Long role_id;


}
