package com.sefarm.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.user.UserRankMapper;
import com.sefarm.model.user.UserRankDO;
import com.sefarm.service.user.IUserRankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户等级的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("userRankService")
public class UserRankServiceImpl extends BaseServiceImpl<UserRankMapper, UserRankDO> implements IUserRankService {

    @Override
    public PageInfo<UserRankDO> getUserRankDOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd) {
        Example example = new Example(UserRankDO.class);
        Example.Criteria criteria = example.createCriteria();
        String sort = Constant.DEFAULT_QUERY_SORT;
        String order = Constant.DEFAULT_QUERY_ORDER;
        //根据等级名查询相应的所有等级信息
        if (StringUtils.isNotBlank(name)) {
            criteria.andEqualTo("name", name);
        }
        //创建开始时间
        if (StringUtils.isNotBlank(createTimeBegin)) {
            criteria.andGreaterThanOrEqualTo(Constant.DEFAULT_QUERY_SORT, createTimeBegin);
        }
        //创建结束时间
        if (StringUtils.isNotBlank(createTimeEnd)) {
            criteria.andLessThanOrEqualTo(Constant.DEFAULT_QUERY_SORT, createTimeEnd);
        }
        //排序升降幂
        if (StringUtils.isNotBlank(sortStr)) {
            sort = sortStr;
        }
        if (StringUtils.isNotBlank(orderStr)) {
            order = orderStr;
        }
        if (Constant.ORDER_DESC.equals(order)) {
            example.orderBy(sort).desc();
        } else if (Constant.ORDER_ASC.equals(order)) {
            example.orderBy(sort).asc();
        }
        //分页
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<UserRankDO> list = getMapper().selectByExample(example);
        PageInfo<UserRankDO> page = new PageInfo<>(list);
        return page;
    }

}
