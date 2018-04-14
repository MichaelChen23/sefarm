package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.model.system.AdvertDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface AdvertMapper extends SeFarmMapper<AdvertDO> {

    /**
     * 按条件查询 广告列表
     * @param name
     * @param createTimeBegin
     * @param createTimeEnd
     * @param sort
     * @param order
     * @return
     */
    List<AdvertDO> getAdvertDOList(@Param("name")String name, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);
}