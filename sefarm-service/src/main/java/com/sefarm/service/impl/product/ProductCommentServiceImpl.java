package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.product.ProductCommentMapper;
import com.sefarm.model.product.ProductCommentDO;
import com.sefarm.service.product.IProductCommentService;

/**
 * 产品评论的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductCommentServiceImpl extends BaseServiceImpl<ProductCommentMapper, ProductCommentDO> implements IProductCommentService {
}
