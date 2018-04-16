package com.sefarm.dao.product;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.model.product.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ProductMapper extends SeFarmMapper<ProductDO> {

    List<ProductVO> getProductVOList(@Param("name")String name, @Param("productTypeId")Long productTypeId, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    ProductVO getProductVO(@Param("prodId") Long prodId);
}