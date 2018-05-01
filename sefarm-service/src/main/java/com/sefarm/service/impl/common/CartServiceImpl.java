package com.sefarm.service.impl.common;

import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.vo.CartVO;
import com.sefarm.dao.common.CartMapper;
import com.sefarm.model.common.CartDO;
import com.sefarm.service.common.ICartService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车的服务接口实现
 *
 * @author mc
 * @date 2018-5-1
 */
@Service("cartService")
public class CartServiceImpl extends BaseServiceImpl<CartMapper, CartDO> implements ICartService {

    @Override
    public List<CartVO> getCartVOAllListByAccount(String account) {
        return getMapper().getCartVOAllListByAccount(account);
    }
}
