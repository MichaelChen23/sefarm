package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.ProductCommentVO;
import com.sefarm.dao.product.ProductCommentMapper;
import com.sefarm.model.product.ProductCommentDO;
import com.sefarm.service.product.IProductCommentService;

import java.util.List;

/**
 * 产品评论的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductCommentServiceImpl extends BaseServiceImpl<ProductCommentMapper, ProductCommentDO> implements IProductCommentService {

    @Override
    public PageInfo<ProductCommentVO> getProductCommentVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String productName, String orderNo, Integer stars, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<ProductCommentVO> list = getMapper().getProductCommentVOList(name, productName, orderNo, stars, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<ProductCommentVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public ProductCommentVO getProductCommentVO(Long commentId) {
        return getMapper().getProductCommentVO(commentId);
    }
}
