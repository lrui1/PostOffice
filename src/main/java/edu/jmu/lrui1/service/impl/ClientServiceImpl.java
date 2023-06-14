package edu.jmu.lrui1.service.impl;

import edu.jmu.lrui1.entity.Client;
import edu.jmu.lrui1.dao.ClientDao;
import edu.jmu.lrui1.service.IClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户 服务实现类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements IClientService {

}
