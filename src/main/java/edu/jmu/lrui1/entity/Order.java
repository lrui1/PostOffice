package edu.jmu.lrui1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 客户id
     */
    private Long userId;

    /**
     * 国内统一刊号
     */
    private String newspaperId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 时间
     */
    private LocalDateTime time;

    /**
     * 订单状态 0未完成 1完成
     */
    private Integer status;
}
