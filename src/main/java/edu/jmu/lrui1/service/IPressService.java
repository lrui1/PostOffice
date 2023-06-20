package edu.jmu.lrui1.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.jmu.lrui1.entity.Press;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 出版社 服务类
 * </p>
 *
 * @author lrui1
 * @since 2023-06-14
 */
public interface IPressService extends IService<Press> {
    /**
     * 分页接口
     * @param currentPage 当前页
     * @param pageSize 一页的条数
     * @param wrapper 查询条件
     * @return 分页对象
     */
    IPage<Press> selectPage(Long currentPage, Long pageSize, Wrapper<Press> wrapper);
}
