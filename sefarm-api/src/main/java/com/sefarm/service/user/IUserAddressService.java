package com.sefarm.service.user;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.model.user.UserAddressDO;

import java.util.List;

/**
 * 用户地址的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IUserAddressService extends IBaseService<UserAddressDO> {

    /**
     * 移动前端——根据用户帐号和是否默认地址标签 获取所有的用户地址
     * @param account
     * @return
     */
    List<UserAddressDO> getUserAddressDOAllListByAccountAndFlag(String account, String defaultFlag);

    /**
     * 后台——按条件分页查询用户地址list
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param account
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    PageInfo<UserAddressDO> getUserAddressDOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String account, String createTimeBegin, String createTimeEnd);

    /**
     * 根据用户帐号改变其下所有的地址为非默认地址，用于新增默认地址的时候
     * add by mc 2018-5-6
     * @param account
     * @return
     */
    Boolean updateAllDefaultFlag(String account);

    /**
     * 根据用户帐号和需要改变为默认地址的用户地址id改变其他地址为非默认地址，用于更新默认地址的时候
     * @param id
     * @param account
     * @return
     */
    Boolean updateOtherDefaultFlagById(Long id, String account);
}
