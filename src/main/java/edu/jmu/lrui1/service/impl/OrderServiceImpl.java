package edu.jmu.lrui1.service.impl;

import edu.jmu.lrui1.entity.Order;
import edu.jmu.lrui1.dao.OrderDao;
import edu.jmu.lrui1.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
