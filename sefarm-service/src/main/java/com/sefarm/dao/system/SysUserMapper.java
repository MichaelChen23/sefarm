package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.model.system.SysUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface SysUserMapper extends SeFarmMapper<SysUserDO> {

    List<SysUserVO> getSysUserVOList(@Param("sysDeptId")Long sysDeptId, @Param("name")String name, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    SysUserVO getSysUserVO(@Param("sysUserId")Long sysUserId);
}