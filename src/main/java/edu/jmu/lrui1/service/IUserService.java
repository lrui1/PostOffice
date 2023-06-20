package edu.jmu.lrui1.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.jmu.lrui1.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
public interface IUserService extends IService<User> {
    /**
     * 查询数据库对应的用户信息
     * @param username 用户名
     * @return 返回数据库用户
     */
    User getUserByUsername(String username);

    /**
     * 分页接口
     * @param currentPage 当前页
     * @param pageSize 一页的条数
     * @param wrapper 查询条件
     * @return 分页对象
     */
    IPage<User> selectPage(Long currentPage, Long pageSize, Wrapper<User> wrapper);
}
