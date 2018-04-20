package com.sefarm.common.base;

import java.util.List;

/**
 * 基础服务接口
 *
 * @author mc
 * @date 2018-3-18
 */
public interface IBaseService<T extends BaseDO> {

    Boolean saveByObj(T DO);

    Boolean removeByObj(T DO);

    Boolean batchRemoveByIds(List<String> list);

    Boolean updateByObj(T DO);

    T getOneByObj(T DO);

    List<T> getListByObj(T DO);

    List<T> getALL();

    List<T> getAllByObj(T DO);

    Integer getCount(T DO);

    List<T> searchListByKV(T DO);
}
