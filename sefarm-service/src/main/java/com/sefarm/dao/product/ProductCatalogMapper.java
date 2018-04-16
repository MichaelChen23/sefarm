package com.sefarm.dao.product;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.model.product.ProductCatalogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品目录的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ProductCatalogMapper extends SeFarmMapper<ProductCatalogDO> {

    /**
     * 按条件分页查询 产品目录 列表
     * @param name
     * @param createTimeBegin
     * @param createTimeEnd
     * @param sort
     * @param order
     * @return
     */
    List<ProductCatalogDO> getProductCatalogDOList(@Param("name")String name, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);
}