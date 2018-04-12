package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.dao.system.SysDeptMapper;
import com.sefarm.model.system.SysDeptDO;
import com.sefarm.service.system.ISysDeptService;

import java.util.List;

/**
 * 系统部门的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDeptDO> implements ISysDeptService {

    @Override
    public List<ZTreeNode> getDeptTree() {
        return getMapper().getDeptTree();
    }
}
