package com.sefarm.service.common;

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

}
