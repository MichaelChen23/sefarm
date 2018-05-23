package com.sefarm.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.user.UserAddressMapper;
import com.sefarm.model.user.UserAddressDO;
import com.sefarm.service.user.IUserAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户地址的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("userAddressService")
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddressMapper, UserAddressDO> implements IUserAddressService {

    @Override
    public List<UserAddressDO> getUserAddressDOAllListByAccountAndFlag(String account, String defaultFlag) {
        Example example = new Example(UserAddressDO.class);
        Example.Criteria criteria = example.createCriteria();
        //根据用户帐号查询相应的所有用户地址
        criteria.andEqualTo("account", account);
        //是否默认地址
        if (StringUtils.isNotBlank(defaultFlag)) {
            criteria.andEqualTo("defaultFlag", defaultFlag);
        }
        example.orderBy("defaultFlag").desc();
        List<UserAddressDO> list = getMapper().selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<UserAddressDO> getUserAddressDOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String account, String createTimeBegin, String createTimeEnd) {
        Example example = new Example(UserAddressDO.class);
        Example.Criteria criteria = example.createCriteria();
        String sort = Constant.DEFAULT_QUERY_SORT;
        String order = Constant.DEFAULT_QUERY_ORDER;
        //根据用户帐号查询相应的所有用户地址
        if (StringUtils.isNotBlank(account)) {
            criteria.andEqualTo("account", account);
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
        List<UserAddressDO> list = getMapper().selectByExample(example);
        PageInfo<UserAddressDO> page = new PageInfo<>(list);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = Constant.DEFAULT_TRANSACTION_TIMEOUT, rollbackFor = Exception.class)
    @Override
    public Boolean updateAllDefaultFlag(String account) {
        Boolean result = false;
        Example example = new Example(UserAddressDO.class);
        Example.Criteria criteria = example.createCriteria();
        //根据用户帐号查询相应的所有用户地址
        if (StringUtils.isNotBlank(account)) {
            criteria.andEqualTo("account", account);
        }
        List<UserAddressDO> list = getMapper().selectByExample(example);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                UserAddressDO userAddressDO = list.get(i);
                userAddressDO.setDefaultFlag(Constant.STATUS_LOCK);
                int resultCode = getMapper().updateByPrimaryKeySelective(userAddressDO);
                if (i == list.size() - 1) {
                    result = resultCode > 0 ? true : false;
                }
            }
        } else {
            result = true;
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = Constant.DEFAULT_TRANSACTION_TIMEOUT, rollbackFor = Exception.class)
    @Override
    public Boolean updateOtherDefaultFlagById(Long id, String account) {
        Boolean result = false;
        Example example = new Example(UserAddressDO.class);
        Example.Criteria criteria = example.createCriteria();
        //根据用户帐号查询相应的所有用户地址
        if (StringUtils.isNotBlank(account)) {
            criteria.andEqualTo("account", account);
        }
        List<UserAddressDO> list = getMapper().selectByExample(example);
        for (int i = 0; i < list.size(); i++) {
            UserAddressDO userAddressDO = list.get(i);
            if (id.equals(userAddressDO.getId())) {
                userAddressDO.setDefaultFlag(Constant.STATUS_UNLOCK);
            } else {
                userAddressDO.setDefaultFlag(Constant.STATUS_LOCK);
            }
            int resultCode = getMapper().updateByPrimaryKeySelective(userAddressDO);
            if (i == list.size()-1) {
                result = resultCode > 0 ? true : false;
            }
        }
        return result;
    }
}
