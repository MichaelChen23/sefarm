package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.product.ProductCatalogMapper;
import com.sefarm.model.product.ProductCatalogDO;
import com.sefarm.service.product.IProductCatalogService;

/**
 * 产品目录的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductCatalogServiceImpl extends BaseServiceImpl<ProductCatalogMapper, ProductCatalogDO> implements IProductCatalogService {
}
