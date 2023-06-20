package edu.jmu.lrui1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.jmu.lrui1.controller.ex.NullException;
import edu.jmu.lrui1.dto.DtoPage;
import edu.jmu.lrui1.dto.Result;
import edu.jmu.lrui1.entity.Press;
import edu.jmu.lrui1.service.IPressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 出版社 前端控制器
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/press")
public class PressController {
    @Autowired
    private IPressService pressService;

    /**
     * 查询所有出版社
     * @return 统一结果类
     */
    @GetMapping
    public Result getAll() {
        List<Press> presses = pressService.list();
        return Result.success(presses);
    }

    /**
     * 分页查询
     * @param page 需要当前页数、页面记录数
     * @return data中是page的各个参数
     */
    @PostMapping("/page")
    public Result selectPage(@RequestBody DtoPage page) {
        IPage<Press> iPage = pressService.selectPage(page.getCurrentPage(), page.getPageSize(), null);
        DtoPage res = new DtoPage(iPage.getCurrent(), iPage.getSize(), iPage.getPages(), iPage.getTotal(), iPage.getRecords());
        return Result.success(res);
    }

    /**
     * 新建用户
     * @param press 接收用户名、密码、
     * @return 统一结果类
     */
    @PostMapping
    public Result saveUser(@RequestBody Press press) {
        // 输入不能为空
        if(press == null || press.getPressId() == null || press.getAddress() == null || press.getName() == null
                || press.getTel() == null) {
            throw new NullException("非法输入");
        }
        pressService.save(press);
        return Result.success("添加成功");
    }

    /**
     * 更新对应id的数据
     * @param press id必须要有，动态更新
     * @return 统一结果类
     */
    @PutMapping
    public Result update(@RequestBody Press press) {
        if(press == null || press.getPressId() == null) {
            throw new NullException("非法输入");
        }
        LambdaQueryWrapper<Press> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Press::getPressId, press.getPressId());
        pressService.update(press, lqw);
        return Result.success("更新成功");
    }

    /**
     * 删除数据
     * @param ids id数组
     * @return 统一结果类
     */
    @DeleteMapping
    public Result deleteList(@RequestBody List<String> ids) {
        if(ids == null || ids.size() == 0 || ids.get(0) == null) {
            throw new NullException("非法输入");
        }
        LambdaQueryWrapper<Press> lqw = new LambdaQueryWrapper<>();
        lqw.in(Press::getPressId, ids);
        pressService.remove(lqw);
        return Result.success("删除成功");
    }

}

