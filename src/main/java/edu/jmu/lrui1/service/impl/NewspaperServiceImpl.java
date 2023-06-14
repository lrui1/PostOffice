package edu.jmu.lrui1.service.impl;

import edu.jmu.lrui1.entity.Newspaper;
import edu.jmu.lrui1.dao.NewspaperDao;
import edu.jmu.lrui1.service.INewspaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报纸表 服务实现类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Service
public class NewspaperServiceImpl extends ServiceImpl<NewspaperDao, Newspaper> implements INewspaperService {

}
