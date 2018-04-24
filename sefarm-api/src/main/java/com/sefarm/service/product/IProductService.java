package com.sefarm.service.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.model.product.ProductDO;

import java.util.List;

/**
 * 产品的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IProductService extends IBaseService<ProductDO> {

    PageInfo<ProductVO> getProductVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, Long productTypeId, String createTimeBegin, String createTimeEnd);

    ProductVO getProductVO(Long prodId);

    /**
     * 移动前端——根据产品类型id来查找相应的产品list
     * @param typeId 产品类型id
     * @return
     */
    List<ProductDO> getProductListByTypeId(Long typeId);
}
