package com.sefarm.service.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.ProductTypeVO;
import com.sefarm.model.product.ProductTypeDO;

import java.util.List;

/**
 * 产品类型的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IProductTypeService extends IBaseService<ProductTypeDO> {

    PageInfo<ProductTypeVO> getProductTypeVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, Long productCatalogId, String createTimeBegin, String createTimeEnd);

    ProductTypeVO getProductTypeVO(Long prodTypeId);

    /**
     * 移动前端——根据产品目录id来查找相应的产品类型list
     * @param catalogId 产品目录id
     * @return
     */
    List<ProductTypeDO> getProductTypeListByCatalogId(Long catalogId);
}
