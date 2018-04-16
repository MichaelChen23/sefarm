package com.sefarm.dao.product;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.ProductTypeVO;
import com.sefarm.model.product.ProductTypeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品类型的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ProductTypeMapper extends SeFarmMapper<ProductTypeDO> {

    List<ProductTypeVO> getProductTypeVOList(@Param("name")String name, @Param("productCatalogId")Long productCatalogId, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    ProductTypeVO getProductTypeVO(@Param("prodTypeId") Long prodTypeId);
}