package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.product.ProductMapper;
import com.sefarm.model.product.ProductDO;
import com.sefarm.service.product.IProductService;

/**
 * 产品的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, ProductDO> implements IProductService {
}
