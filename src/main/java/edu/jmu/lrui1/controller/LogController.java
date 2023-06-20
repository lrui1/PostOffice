package edu.jmu.lrui1.controller;

import edu.jmu.lrui1.common.util.SaltUtils;
import edu.jmu.lrui1.controller.ex.NullException;
import edu.jmu.lrui1.dto.DtoUser;
import edu.jmu.lrui1.dto.Result;
import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lrui1
 * @description 登录控制器
 * @date: 2023/6/17
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private IUserService userService;

    @GetMapping("/toLogin")
    public Result toLogin() {
        return Result.success("请先登录");
    }

    /**
     * 登录
     * @param user 用户名、密码
     * @return 统一结果类
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
        User dbUser = userService.getUserByUsername(user.getUsername());
        return Result.success(new DtoUser(dbUser.getUsername(), dbUser.getIdentity()));
    }

    /**
     * 登出
     * @return 统一结果类
     */
    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("登出成功");
    }

    /**
     * 获取当前登录用户
     * @return 统一结果类
     */
    @GetMapping("/user")
    public Result getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return Result.success(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if(user == null || user.getUsername() == null || user.getPassword() == null || user.getTel() == null
                || user.getAddress() == null || user.getPostalCode() == null) {
            throw new NullException("非法输入");
        }
        user.setSalt(SaltUtils.getSalt(SaltUtils.count));
        // 给密码加密 加盐salt
        String newPassword = new SimpleHash("md5", user.getPassword(), user.getSalt(), 1).toString();
        user.setPassword(newPassword);
        userService.save(user);
        return Result.success("注册成功");
    }
}
