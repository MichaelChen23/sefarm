package com.sefarm.dao.user;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.model.user.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户的数据访问层Mapper
 *
 * @author mc
 * @date 2018-5-15
 */
public interface UserMapper extends SeFarmMapper<UserDO> {

    /**
     * 按条件查询用户地址list
     * @param name
     * @param mobile
     * @param address
     * @param sexInt
     * @param status
     * @param createTimeBegin
     * @param createTimeEnd
     * @param lastLoginTimeBegin
     * @param lastLoginTimeEnd
     * @param sort
     * @param order
     * @return
     */
    List<UserDO> getUserDOList(@Param("name")String name, @Param("mobile")String mobile, @Param("address")String address, @Param("sex")Integer sexInt, @Param("status")String status,
                               @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("lastLoginTimeBegin")String lastLoginTimeBegin, @Param("lastLoginTimeEnd")String lastLoginTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    /**
     * 保存用户信息，并且返回id
     * @param userDO
     * @return
     */
    Integer saveUserByObj(UserDO userDO);
}