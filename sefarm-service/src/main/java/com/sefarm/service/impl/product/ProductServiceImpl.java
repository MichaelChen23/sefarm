package com.sefarm.service.impl.product;

import com.alibaba.dubbo.config.annotation.Service;
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
import tk.mybatis.mapper.entity.Example;

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
}
