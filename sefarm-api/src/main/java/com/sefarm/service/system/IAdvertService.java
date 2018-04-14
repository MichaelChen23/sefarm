package com.sefarm.service.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.model.system.AdvertDO;

/**
 * 广告的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IAdvertService extends IBaseService<AdvertDO> {

    /**
     * 按条件分页查询 广告列表
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param name
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    PageInfo<AdvertDO> getAdvertDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd);
}
