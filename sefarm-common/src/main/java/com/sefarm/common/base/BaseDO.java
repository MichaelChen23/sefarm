package com.sefarm.common.base;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 基础实体信息
 *
 * @author mc
 * @date 2018-3-18
 */
public class BaseDO implements Serializable {

    @Transient
    private Integer pageIndex = 1;

    @Transient
    private Integer pageSize = 10;

    /**
     * searchKey-查询的字段
     */
    @Transient
    private String searchKey;

    /**
     * searchValue-查询字段的值
     */
    @Transient
    private String searchValue;

    /**
     * 创建开始时间
     */
    @Transient
    String createTimeBegin;

    /**
     * 创建结束时间
     */
    @Transient
    String createTimeEnd;

    /**
     * 排序的字段
     */
    @Transient
    String sort;

    /**
     * 排序的升降
     */
    @Transient
    String order;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", searchKey='" + searchKey + '\'' +
                ", searchValue='" + searchValue + '\'' +
                ", createTimeBegin='" + createTimeBegin + '\'' +
                ", createTimeEnd='" + createTimeEnd + '\'' +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
