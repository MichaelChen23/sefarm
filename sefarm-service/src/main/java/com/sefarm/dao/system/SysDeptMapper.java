package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysDeptVO;
import com.sefarm.model.system.SysDeptDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统部门的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface SysDeptMapper extends SeFarmMapper<SysDeptDO> {

    /**
     * 获取部门组织的Ztree
     * @return
     */
    List<ZTreeNode> getDeptTree();

    List<SysDeptVO> getSysDeptVOList(@Param("name")String name, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    SysDeptVO getSysDeptVO(@Param("sysDeptId") Long sysDeptId);
}