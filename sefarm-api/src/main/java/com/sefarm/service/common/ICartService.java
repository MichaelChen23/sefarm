package com.sefarm.service.common;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.CartVO;
import com.sefarm.model.common.CartDO;

import java.util.List;

/**
 * 购物车的服务接口
 *
 * @author mc
 * @date 2018-5-1
 */
public interface ICartService extends IBaseService<CartDO> {

    /**
     * 移动前端——根据用户帐号来查找所有的购物车产品list
     * @param account
     * @return
     */
    List<CartVO> getCartVOAllListByAccount(String account);

    /**
     * 后台——按条件分页查询购物车产品list
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param account
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    PageInfo<CartVO> getCartVOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String account, String createTimeBegin, String createTimeEnd);

    /**
     * 后台——根据购物车id获取产品信息
     * @param cartId
     * @return
     */
    CartVO getCartVO(Long cartId);

}
