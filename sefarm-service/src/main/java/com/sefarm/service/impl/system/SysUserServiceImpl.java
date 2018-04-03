package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.dao.system.SysUserMapper;
import com.sefarm.model.system.SysUserDO;
import com.sefarm.service.system.ISysUserService;

import java.util.List;

/**
 * 系统用户的服务接口实现
 *
 * @author mc
 * @date 2018-3-18
 */
@Service(version = "1.0.0")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {

    @Override
    public PageInfo<SysUserVO> getSysUserVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<SysUserVO> list = getMapper().getSysUserVOList(name, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<SysUserVO> page = new PageInfo<>(list);
        return page;
    }

}
