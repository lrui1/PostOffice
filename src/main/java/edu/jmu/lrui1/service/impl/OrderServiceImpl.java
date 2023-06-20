package edu.jmu.lrui1.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jmu.lrui1.entity.Order;
import edu.jmu.lrui1.dao.OrderDao;
import edu.jmu.lrui1.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements IOrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public IPage<Order> selectPage(Long currentPage, Long pageSize, Wrapper<Order> wrapper) {
        IPage<Order> page=new Page<>(currentPage,pageSize);
        orderDao.selectPage(page,wrapper);
        return page;
    }
}
