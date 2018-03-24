package com.sefarm.common.base;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 基础服务接口实现
 *
 * @author mc
 * @date 2018-3-18
 */
public class BaseServiceImpl<M extends SeFarmMapper<T>, T extends BaseDO> implements IBaseService<T> {

    @Autowired
    private M mapper;

    public M getMapper() {
        return mapper;
    }

    @Override
    public Boolean saveByObj(T DO) {
        return mapper.insertSelective(DO) != 0;
    }

    @Override
    public Boolean removeByObj(T DO) {
        return mapper.delete(DO) != 0;
    }

    @Override
    public Boolean batchRemoveByIds(List<String> list) {
        if (list != null && list.size() > 0) {
            for (String each : list) {
                mapper.deleteByPrimaryKey(each);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateByObj(T DO) {
        //根据主键进行更新,这里最多只会更新一条数据
        //参数 T 为实体类
        //int updateByPrimaryKey(T record);
        /**
         * 根据主键进行更新
         * 只会更新不是null的数据
         * T 为实体类
         */
        return mapper.updateByPrimaryKeySelective(DO) != 0;
    }

    @Override
    public T getOneByObj(T DO) {
        return mapper.selectOne(DO);
    }

    @Override
    public List<T> getListByObj(T DO) {
        if (DO.getPageIndex() == 0 && DO.getPageSize() > 0) {
            PageHelper.startPage(DO.getPageIndex(), DO.getPageSize());
        } else if (DO.getPageIndex() > 0 && DO.getPageSize() > 0) {
            PageHelper.offsetPage(DO.getPageIndex(), DO.getPageSize());
        }
        return mapper.select(DO);
    }

    @Override
    public List<T> getALL() {
        return mapper.selectAll();
    }

    @Override
    public Integer getCount(T DO) {
        return mapper.selectCount(DO);
    }

    @Override
    public List<T> searchListByKV(T DO) {
        if (DO.getPageIndex() == 0 && DO.getPageSize() > 0) {
            PageHelper.startPage(DO.getPageIndex(), DO.getPageSize());
        } else if (DO.getPageIndex() > 0 && DO.getPageSize() > 0) {
            PageHelper.offsetPage(DO.getPageIndex(), DO.getPageSize());
        }
        Example example = new Example(DO.getClass());
        example.createCriteria().andLike("" + DO.getSearchKey(), "%" + DO.getSearchValue() + "%");
        return mapper.selectByExample(example);
    }

}
