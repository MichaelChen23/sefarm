package com.sefarm.service.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.model.product.ProductDO;

/**
 * 产品的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IProductService extends IBaseService<ProductDO> {

    PageInfo<ProductVO> getProductVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, Long productTypeId, String createTimeBegin, String createTimeEnd);

    ProductVO getProductVO(Long prodId);

}
