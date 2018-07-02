package com.sefarm.service.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysRoleVO;
import com.sefarm.model.system.SysRoleDO;

import java.util.List;

/**
 * 系统角色的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ISysRoleService extends IBaseService<SysRoleDO> {

    List<ZTreeNode> getSysRoleTree();

    PageInfo<SysRoleVO> getSysRoleVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd);

    SysRoleVO getSysRoleVO(Long sysRoleId);

    Boolean setMenuAuthority(Long roleId, String ids, String username);

}
