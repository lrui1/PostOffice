package edu.jmu.lrui1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.jmu.lrui1.controller.ex.NullException;
import edu.jmu.lrui1.dto.DtoPage;
import edu.jmu.lrui1.dto.Result;
import edu.jmu.lrui1.entity.Newspaper;
import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.service.INewspaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 报纸表 前端控制器
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/newspaper")
public class NewspaperController {
    @Autowired
    private INewspaperService newspaperService;

    /**
     * 查询所有用户
     * @return 统一结果类
     */
    @GetMapping
    public Result getAll() {
        List<Newspaper> newspapers = newspaperService.list();
        return Result.success(newspapers);
    }

    /**
     * 分页查询
     * @param page 需要当前页数、页面记录数
     * @return data中是page的各个参数
     */
    @PostMapping("/page")
    public Result selectPage(@RequestBody DtoPage page) {
        IPage<Newspaper> iPage = newspaperService.selectPage(page.getCurrentPage(), page.getPageSize(), null);
        DtoPage res = new DtoPage(iPage.getCurrent(), iPage.getSize(), iPage.getPages(), iPage.getTotal(), iPage.getRecords());
        return Result.success(res);
    }

    /**
     * 新建用户
     * @param newspaper 接收用户名、密码、
     * @return 统一结果类
     */
    @PostMapping
    public Result saveUser(@RequestBody Newspaper newspaper) {
        // 输入不能为空
        if(newspaper == null || newspaper.getNewspaperId() == null || newspaper.getPageSize() == null || newspaper.getName() == null
                || newspaper.getPrice() == null || newspaper.getPressId() == null ) {
            throw new NullException("非法输入");
        }
        newspaperService.save(newspaper);
        return Result.success("添加成功");
    }

    /**
     * 更新对应id的数据
     * @param newspaper id必须要有，动态更新
     * @return 统一结果类
     */
    @PutMapping
    public Result update(@RequestBody Newspaper newspaper) {
        if(newspaper == null || newspaper.getNewspaperId() == null) {
            throw new NullException("非法输入");
        }
        LambdaQueryWrapper<Newspaper> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Newspaper::getNewspaperId, newspaper.getNewspaperId());
        newspaperService.update(newspaper, lqw);
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
        LambdaQueryWrapper<Newspaper> lqw = new LambdaQueryWrapper<>();
        lqw.in(Newspaper::getNewspaperId, ids);
        newspaperService.remove(lqw);
        return Result.success("删除成功");
    }
}

