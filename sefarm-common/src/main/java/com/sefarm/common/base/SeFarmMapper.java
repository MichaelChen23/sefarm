package com.sefarm.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承基础mapper的SeFarmMapper
 *
 * @author mc
 * @date 2017-3-18
 */
public interface SeFarmMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
