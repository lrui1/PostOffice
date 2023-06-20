package edu.jmu.lrui1.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.jmu.lrui1.controller.ex.NullException;
import edu.jmu.lrui1.dto.DtoPage;
import edu.jmu.lrui1.dto.DtoUserOrder;
import edu.jmu.lrui1.dto.Result;
import edu.jmu.lrui1.entity.Order;
import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 查询所有用户
     * @return 统一结果类
     */
    @GetMapping
    public Result getAll() {
        List<Order> orders = orderService.list();
        return Result.success(orders);
    }

    /**
     * 分页查询
     * @param page 需要当前页数、页面记录数
     * @return data中是page的各个参数
     */
    @PostMapping("/page")
    public Result selectPage(@RequestBody DtoPage page) {
        IPage<Order> iPage = orderService.selectPage(page.getCurrentPage(), page.getPageSize(), null);
        DtoPage res = new DtoPage(iPage.getCurrent(), iPage.getSize(), iPage.getPages(), iPage.getTotal(), iPage.getRecords());
        return Result.success(res);
    }

    /**
     * 根据用户分页查询
     * @param dtoUserOrder 需要用户id、分页类
     * @return data中是page的各个参数
     */
    @PostMapping("/page/1")
    public Result selectPageByUser(@RequestBody DtoUserOrder dtoUserOrder) {
        DtoPage page = dtoUserOrder.getPage();
        User user = dtoUserOrder.getUser();
        // 输入不能为空
        if(user == null || user.getUserId() == null) {
            throw new NullException("非法输入");
        }
        Wrapper<Order> wrapper = Wrappers.<Order>lambdaQuery().eq(Order::getUserId, user.getUserId());
        IPage<Order> iPage = orderService.selectPage(page.getCurrentPage(), page.getPageSize(), wrapper);
        DtoPage res = new DtoPage(iPage.getCurrent(), iPage.getSize(), iPage.getPages(), iPage.getTotal(), iPage.getRecords());
        return Result.success(res);
    }

    /**
     * 新建用户
     * @param order 接收订单信息
     * @return 统一结果类
     */
    @PostMapping
    public Result saveOrder(@RequestBody Order order) {
        // 输入不能为空
        if(order == null || order.getNewspaperId() == null || order.getCount() == null
                || order.getUserId() == null) {
            throw new NullException("非法输入");
        }
        orderService.save(order);
        return Result.success("添加成功");
    }

    /**
     * 更新对应id的数据
     * @param order id必须要有，动态更新
     * @return 统一结果类
     */
    @PutMapping
    public Result update(@RequestBody Order order) {
        if(order == null || order.getOrderId() == null) {
            throw new NullException("非法输入");
        }
        orderService.updateById(order);
        return Result.success("更新成功");
    }

    /**
     * 删除数据
     * @param ids id数组
     * @return 统一结果类
     */
    @DeleteMapping
    public Result deleteList(@RequestBody List<Long> ids) {
        if(ids == null || ids.size() == 0 || ids.get(0) == null) {
            throw new NullException("非法输入");
        }
        orderService.removeByIds(ids);
        return Result.success("删除成功");
    }
}

