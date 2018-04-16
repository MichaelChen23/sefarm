package com.sefarm.service.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.model.product.ProductCatalogDO;

/**
 * 产品目录的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IProductCatalogService extends IBaseService<ProductCatalogDO> {

    /**
     * 按条件分页查询 产品目录 列表
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param name
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    PageInfo<ProductCatalogDO> getProductCatalogDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd);
}
