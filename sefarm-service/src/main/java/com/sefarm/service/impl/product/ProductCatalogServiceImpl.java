package com.sefarm.service.impl.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.dao.product.ProductCatalogMapper;
import com.sefarm.model.product.ProductCatalogDO;
import com.sefarm.service.product.IProductCatalogService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 产品目录的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("productCatalogService")
public class ProductCatalogServiceImpl extends BaseServiceImpl<ProductCatalogMapper, ProductCatalogDO> implements IProductCatalogService {
    @Override
    public PageInfo<ProductCatalogDO> getProductCatalogDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<ProductCatalogDO> list = getMapper().getProductCatalogDOList(name, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<ProductCatalogDO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<ProductCatalogDO> getAllProductCatalogList() {
        Example example = new Example(ProductCatalogDO.class);
        //查询出开启状态的产品目录
        example.createCriteria().andEqualTo("status", Constant.STATUS_UNLOCK);
        //按照sort值最大的为先顺序排序
        example.orderBy("sort").desc();
        List<ProductCatalogDO> list = getMapper().selectByExample(example);
        return list;
    }
}
