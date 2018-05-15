package com.sefarm.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.dao.user.UserMapper;
import com.sefarm.model.user.UserDO;
import com.sefarm.service.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserDO> implements IUserService {


    @Override
    public PageInfo<UserDO> getUserDOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String mobile, String address, Integer sexInt, String lockStr, String createTimeBegin, String createTimeEnd, String lastLoginTimeBegin, String lastLoginTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<UserDO> list = getMapper().getUserDOList(name, mobile, address, sexInt, lockStr, createTimeBegin, createTimeEnd, lastLoginTimeBegin, lastLoginTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<UserDO> page = new PageInfo<>(list);
        return page;
    }
}
