package edu.jmu.lrui1.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.jmu.lrui1.common.util.SaltUtils;
import edu.jmu.lrui1.controller.ex.NullException;
import edu.jmu.lrui1.dto.DtoPage;
import edu.jmu.lrui1.dto.Result;
import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户
     * @return 统一结果类
     */
    @GetMapping
    public Result getAll() {
        List<User> users = userService.list();
        return Result.success(users);
    }

    /**
     * 分页查询
     * @param page 需要当前页数、页面记录数
     * @return data中是page的各个参数
     */
    @PostMapping("/page")
    public Result selectPage(@RequestBody DtoPage page) {
        IPage<User> iPage = userService.selectPage(page.getCurrentPage(), page.getPageSize(), null);
        DtoPage res = new DtoPage(iPage.getCurrent(), iPage.getSize(), iPage.getPages(), iPage.getTotal(), iPage.getRecords());
        return Result.success(res);
    }

    /**
     * 新建用户
     * @param user 接收用户名、密码、
     * @return 统一结果类
     */
    @PostMapping
    public Result saveUser(@RequestBody User user) {
        // 输入不能为空
        if(user == null || user.getUsername() == null || user.getPassword() == null || user.getTel() == null
            || user.getAddress() == null || user.getPostalCode() == null || user.getIdentity() == null) {
            throw new NullException("非法输入");
        }
        user.setSalt(SaltUtils.getSalt(SaltUtils.count));
        // 给密码加密 加盐salt
        String newPassword = new SimpleHash("md5", user.getPassword(), user.getSalt(), 1).toString();
        user.setPassword(newPassword);
        userService.save(user);
        return Result.success("添加成功");
    }

    /**
     * 更新对应id的数据
     * @param user id必须要有，动态更新
     * @return 统一结果类
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        if(user == null || user.getUserId() == null) {
            throw new NullException("非法输入");
        }
        // 查数据库的密码 看看是否一样，一样就更新密码
        User dbUser = userService.getById(user.getUserId());
        if(!dbUser.getPassword().equals(user.getPassword())) {
            // 给密码加密 加盐salt
            String newPassword = new SimpleHash("md5", user.getPassword(), user.getSalt(), 1).toString();
            user.setPassword(newPassword);
        }
        userService.updateById(user);
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
        userService.removeByIds(ids);
        return Result.success("删除成功");
    }
}

