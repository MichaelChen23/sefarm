package com.sefarm.service.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysDeptVO;
import com.sefarm.model.system.SysDeptDO;

import java.util.List;

/**
 * 系统部门的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ISysDeptService extends IBaseService<SysDeptDO> {

    /**
     * 获取部门组织的ztree
     *
     * @return
     */
    List<ZTreeNode> getDeptTree();

    PageInfo<SysDeptVO> getSysDeptVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd);

    SysDeptVO getSysDeptVO(Long sysDeptId);
}
