package edu.jmu.lrui1.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jmu.lrui1.entity.Press;
import edu.jmu.lrui1.dao.PressDao;
import edu.jmu.lrui1.service.IPressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出版社 服务实现类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
@Service
public class PressServiceImpl extends ServiceImpl<PressDao, Press> implements IPressService {
    @Autowired
    private PressDao pressDao;

    @Override
    public IPage<Press> selectPage(Long currentPage, Long pageSize, Wrapper<Press> wrapper) {
        IPage<Press> page=new Page<>(currentPage,pageSize);
        pressDao.selectPage(page,wrapper);
        return page;
    }
}
