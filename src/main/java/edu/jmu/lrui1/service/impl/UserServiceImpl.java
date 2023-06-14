package edu.jmu.lrui1.service.impl;

import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.dao.UserDao;
import edu.jmu.lrui1.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
