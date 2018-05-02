package com.sefarm.service.impl.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
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

    @Override
    public PageInfo<CartVO> getCartVOPageList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String account, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<CartVO> list = getMapper().getCartVOList(account, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<CartVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public CartVO getCartVO(Long cartId) {
        return getMapper().getCartVO(cartId);
    }
}
