package com.sefarm.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.dao.system.SysUserMapper;
import com.sefarm.model.system.SysUserDO;
import com.sefarm.service.system.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统用户的服务接口实现
 *
 * @author mc
 * @date 2018-3-18
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {

    @Override
    public PageInfo<SysUserVO> getSysUserVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, Long sysDeptId,  String name, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<SysUserVO> list = getMapper().getSysUserVOList(sysDeptId, name, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<SysUserVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public SysUserVO getSysUserVO(Long sysUserId) {
        return getMapper().getSysUserVO(sysUserId);
    }

    @Override
    public SysUserVO getSysUserVOByUsername(String username) {
        return getMapper().getSysUserVOByUsername(username);
    }

}
