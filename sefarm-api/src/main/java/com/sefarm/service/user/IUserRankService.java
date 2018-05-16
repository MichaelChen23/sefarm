package com.sefarm.service.user;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.model.user.UserRankDO;

/**
 * 用户等级的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IUserRankService extends IBaseService<UserRankDO> {

    /**
     * 后台——按条件分页查询用户等级list
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param name
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    PageInfo<UserRankDO> getUserRankDOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd);
}
