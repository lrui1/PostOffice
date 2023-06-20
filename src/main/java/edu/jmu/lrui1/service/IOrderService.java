package edu.jmu.lrui1.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.jmu.lrui1.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
public interface IOrderService extends IService<Order> {
    /**
     * 分页接口
     * @param currentPage 当前页
     * @param pageSize 一页的条数
     * @param wrapper 查询条件
     * @return 分页对象
     */
    IPage<Order> selectPage(Long currentPage, Long pageSize, Wrapper<Order> wrapper);
}
