package com.sefarm.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.SysDeptVO;
import com.sefarm.dao.system.SysDeptMapper;
import com.sefarm.model.system.SysDeptDO;
import com.sefarm.service.system.ISysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统部门的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("sysDeptService")
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDeptDO> implements ISysDeptService {

    @Override
    public List<ZTreeNode> getDeptTree() {
        return getMapper().getDeptTree();
    }

    @Override
    public PageInfo<SysDeptVO> getSysDeptVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<SysDeptVO> list = getMapper().getSysDeptVOList(name, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<SysDeptVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public SysDeptVO getSysDeptVO(Long sysDeptId) {
        return getMapper().getSysDeptVO(sysDeptId);
    }
}
