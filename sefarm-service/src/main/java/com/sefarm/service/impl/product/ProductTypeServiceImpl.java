package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.ProductTypeVO;
import com.sefarm.dao.product.ProductTypeMapper;
import com.sefarm.model.product.ProductTypeDO;
import com.sefarm.service.product.IProductTypeService;

import java.util.List;

/**
 * 产品类型的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductTypeMapper, ProductTypeDO> implements IProductTypeService {

    @Override
    public PageInfo<ProductTypeVO> getProductTypeVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, Long productCatalogId, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<ProductTypeVO> list = getMapper().getProductTypeVOList(name, productCatalogId, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<ProductTypeVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public ProductTypeVO getProductTypeVO(Long prodTypeId) {
        return getMapper().getProductTypeVO(prodTypeId);
    }
}
