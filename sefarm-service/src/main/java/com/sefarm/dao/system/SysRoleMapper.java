package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysRoleVO;
import com.sefarm.model.system.SysRoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface SysRoleMapper extends SeFarmMapper<SysRoleDO> {

    List<ZTreeNode> getSysRoleTree();

    List<SysRoleVO> getSysRoleVOList(@Param("name")String name, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    SysRoleVO getSysRoleVO(@Param("sysRoleId") Long sysRoleId);

}