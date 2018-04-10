package com.sefarm.service.system;

import com.sefarm.common.base.IBaseService;
import com.sefarm.common.node.ZTreeNode;
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
}
