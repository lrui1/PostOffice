package edu.jmu.lrui1.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jmu.lrui1.entity.Newspaper;
import edu.jmu.lrui1.dao.NewspaperDao;
import edu.jmu.lrui1.service.INewspaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private NewspaperDao newspaperDao;

    @Override
    public IPage<Newspaper> selectPage(Long currentPage, Long pageSize, Wrapper<Newspaper> wrapper) {
        IPage<Newspaper> page=new Page<>(currentPage,pageSize);
        newspaperDao.selectPage(page,wrapper);
        return page;
    }
}
