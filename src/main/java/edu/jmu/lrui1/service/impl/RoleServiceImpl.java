package edu.jmu.lrui1.service.impl;

import edu.jmu.lrui1.entity.Role;
import edu.jmu.lrui1.dao.RoleDao;
import edu.jmu.lrui1.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

}
