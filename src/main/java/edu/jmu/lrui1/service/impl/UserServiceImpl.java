package edu.jmu.lrui1.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.dao.UserDao;
import edu.jmu.lrui1.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username);
        return userDao.selectOne(lqw);
    }

    @Override
    public IPage<User> selectPage(Long currentPage, Long pageSize, Wrapper<User> wrapper) {
        IPage<User> page=new Page<>(currentPage,pageSize);
        userDao.selectPage(page,wrapper);
        return page;
    }
}
