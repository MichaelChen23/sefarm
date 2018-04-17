package com.sefarm.service.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.ProductCommentVO;
import com.sefarm.model.product.ProductCommentDO;

/**
 * 产品评论的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IProductCommentService extends IBaseService<ProductCommentDO> {

    PageInfo<ProductCommentVO> getProductCommentVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String productName, String orderNo, Integer stars, String createTimeBegin, String createTimeEnd);

    ProductCommentVO getProductCommentVO(Long commentId);

}
