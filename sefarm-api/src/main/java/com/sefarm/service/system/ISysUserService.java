package com.sefarm.service.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.model.system.SysUserDO;

/**
 * 系统用户的服务接口
 *
 * @author mc
 * @date 2018-3-18
 */
public interface ISysUserService extends IBaseService<SysUserDO> {

    PageInfo<SysUserVO> getSysUserVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd);

}
