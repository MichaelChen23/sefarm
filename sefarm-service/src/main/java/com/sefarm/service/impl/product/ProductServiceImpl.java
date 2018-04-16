package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.dao.product.ProductMapper;
import com.sefarm.model.product.ProductDO;
import com.sefarm.service.product.IProductService;

import java.util.List;

/**
 * 产品的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, ProductDO> implements IProductService {

    @Override
    public PageInfo<ProductVO> getProductVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, Long productTypeId, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<ProductVO> list = getMapper().getProductVOList(name, productTypeId, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<ProductVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public ProductVO getProductVO(Long prodId) {
        return getMapper().getProductVO(prodId);
    }
}
