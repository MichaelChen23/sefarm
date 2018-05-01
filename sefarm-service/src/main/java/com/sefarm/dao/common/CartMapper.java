package com.sefarm.dao.common;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.CartVO;
import com.sefarm.model.common.CartDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车的数据访问层Mapper
 *
 * @author mc
 * @date 2018-5-1
 */
public interface CartMapper extends SeFarmMapper<CartDO> {

    List<CartVO> getCartVOAllListByAccount(@Param("account")String account);
}