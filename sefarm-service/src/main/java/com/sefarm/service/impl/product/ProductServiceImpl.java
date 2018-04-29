package com.sefarm.service.impl.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.constant.state.ProductStatus;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.dao.product.ProductMapper;
import com.sefarm.model.product.ProductDO;
import com.sefarm.service.product.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 产品的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("productService")
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

    @Override
    public List<ProductDO> getProductListByTypeId(Long typeId) {
        Example example = new Example(ProductDO.class);
        //根据产品类型id查询相应产品list，并且是状态排除 已下架off和已删除del
        example.createCriteria().andEqualTo("productTypeId", typeId)
                .andNotEqualTo("status", ProductStatus.OFF.getCode())
                .andNotEqualTo("status", ProductStatus.DEL.getCode());
        //按照sort值最大的为先顺序排序
        example.orderBy("sort").desc();
        List<ProductDO> list = getMapper().selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<ProductDO> searchProductDOPageList(Integer pageIndex, Integer pageSize, String name) {
        Example example = new Example(ProductDO.class);
        Example.Criteria criteria = example.createCriteria();
        //根据产品name查询相应产品list
        if (StringUtils.isNotBlank(name)) {
            criteria.andLike("name", "%"+name+"%");
        } else {
            criteria.orEqualTo("saleFlag", Constant.STATUS_UNLOCK);
            criteria.orEqualTo("newFlag", Constant.STATUS_UNLOCK);
        }
        //状态排除 已下架off和已删除del
        criteria.andNotEqualTo("status", ProductStatus.OFF.getCode())
                .andNotEqualTo("status", ProductStatus.DEL.getCode());
        example.setOrderByClause("sale_flag desc, new_flag desc, sort desc");
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<ProductDO> list = getMapper().selectByExample(example);
        PageInfo<ProductDO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public PageInfo<ProductDO> getProductDOPageList(Integer pageIndex, Integer pageSize, Long typeId, String name, String newFlag, String saleFlag, String sortStr, String orderStr) {
        Example example = new Example(ProductDO.class);
        Example.Criteria criteria = example.createCriteria();
        String sort = Constant.PRODUCT_DEFAULT_QUERY_SORT;
        String order = Constant.PRODUCT_DEFAULT_QUERY_ORDER;
        if (typeId != null && typeId > 0) {
            criteria.andEqualTo("productTypeId", typeId);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andLike("name", "%"+name+"%");
        }
        if (StringUtils.isNotBlank(newFlag)) {
            criteria.andEqualTo("newFlag", Constant.STATUS_UNLOCK);
        }
        if (StringUtils.isNotBlank(saleFlag)) {
            criteria.andEqualTo("saleFlag", Constant.STATUS_UNLOCK);
        }
        //排除些下架和删除的产品
        criteria.andNotEqualTo("status", ProductStatus.OFF.getCode())
                .andNotEqualTo("status", ProductStatus.DEL.getCode());
        if (StringUtils.isNotBlank(sortStr)) {
            sort = sortStr;
        }
        if (StringUtils.isNotBlank(orderStr)) {
            order = orderStr;
        }
        if (Constant.ORDER_DESC.equals(order)) {
            example.orderBy(sort).desc();
        } else if (Constant.ORDER_ASC.equals(order)) {
            example.orderBy(sort).asc();
        }
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<ProductDO> list = getMapper().selectByExample(example);
        PageInfo<ProductDO> page = new PageInfo<>(list);
        return page;
    }
}
