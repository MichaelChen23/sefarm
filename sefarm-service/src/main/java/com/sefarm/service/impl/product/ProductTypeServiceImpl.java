package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.product.ProductTypeMapper;
import com.sefarm.model.product.ProductTypeDO;
import com.sefarm.service.product.IProductTypeService;

/**
 * 产品类型的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductTypeMapper, ProductTypeDO> implements IProductTypeService {
}
