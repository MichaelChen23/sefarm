package com.sefarm.service.user;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.model.user.UserDO;

/**
 * 用户的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IUserService extends IBaseService<UserDO> {

    /**
     * 后台——按条件分页查询用户地址list
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param name
     * @param mobile
     * @param address
     * @param sexInt
     * @param lockStr
     * @param createTimeBegin
     * @param createTimeEnd
     * @param lastLoginTimeBegin
     * @param lastLoginTimeEnd
     * @return
     */
    PageInfo<UserDO> getUserDOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String mobile,
                                       String address, Integer sexInt, String lockStr, String createTimeBegin, String createTimeEnd, String lastLoginTimeBegin, String lastLoginTimeEnd);

}
