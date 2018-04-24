package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.ProductTypeVO;
import com.sefarm.dao.product.ProductTypeMapper;
import com.sefarm.model.product.ProductTypeDO;
import com.sefarm.service.product.IProductTypeService;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<ProductTypeDO> getProductTypeListByCatalogId(Long catalogId) {
        Example example = new Example(ProductTypeDO.class);
        //根据产品目录id查询相应产品类型list，并且是开启状态的
        example.createCriteria().andEqualTo("productCatalogId", catalogId).andEqualTo("status", Constant.STATUS_UNLOCK);
        //按照sort值最大的为先顺序排序
        example.orderBy("sort").desc();
        List<ProductTypeDO> list = getMapper().selectByExample(example);
        return list;
    }
}
