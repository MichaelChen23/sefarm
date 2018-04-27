package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sys_menu")
public class SysMenuDO extends BaseDO implements Serializable {
    /**
     * 系统菜单ID 
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接
     */
    private String url;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 详述 前端beetl框架对description字段处理有问题，所以改为detail字段
     * modify by mc 2018-4-27
     */
    private String detail;

    /**
     * 是否菜单：1-是；0-不是
     */
    @Column(name = "is_menu")
    private Integer isMenu;

    /**
     * 菜单层级，最高层是1
     */
    private Integer levels;

    /**
     * 父菜单编号
     */
    private String pcode;

    /**
     * 所有父菜单编号集合，用‘,’隔开
     */
    private String pcodes;

    /**
     * 是否打开：1-打开；0-不打开，默认为1
     */
    @Column(name = "is_open")
    private Integer isOpen;

    /**
     * 状态：y-启用；n-禁用，默认为y
     */
    private String status;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取系统菜单ID 
     *
     * @return id - 系统菜单ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统菜单ID 
     *
     * @param id 系统菜单ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取菜单名称
     *
     * @return name - 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单编号
     *
     * @return code - 菜单编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置菜单编号
     *
     * @param code 菜单编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取链接
     *
     * @return url - 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接
     *
     * @param url 链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取排序号，1最小，默认为1
     *
     * @return sort - 排序号，1最小，默认为1
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序号，1最小，默认为1
     *
     * @param sort 排序号，1最小，默认为1
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取详述
     *
     * @return detail - 详述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置详述
     *
     * @param detail 详述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取是否菜单：1-是；0-不是
     *
     * @return is_menu - 是否菜单：1-是；0-不是
     */
    public Integer getIsMenu() {
        return isMenu;
    }

    /**
     * 设置是否菜单：1-是；0-不是
     *
     * @param isMenu 是否菜单：1-是；0-不是
     */
    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    /**
     * 获取菜单层级，最高层是1
     *
     * @return levels - 菜单层级，最高层是1
     */
    public Integer getLevels() {
        return levels;
    }

    /**
     * 设置菜单层级，最高层是1
     *
     * @param levels 菜单层级，最高层是1
     */
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    /**
     * 获取父菜单编号
     *
     * @return pcode - 父菜单编号
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * 设置父菜单编号
     *
     * @param pcode 父菜单编号
     */
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    /**
     * 获取所有父菜单编号集合，用‘,’隔开
     *
     * @return pcodes - 所有父菜单编号集合，用‘,’隔开
     */
    public String getPcodes() {
        return pcodes;
    }

    /**
     * 设置所有父菜单编号集合，用‘,’隔开
     *
     * @param pcodes 所有父菜单编号集合，用‘,’隔开
     */
    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
    }

    /**
     * 获取是否打开：1-打开；0-不打开，默认为1
     *
     * @return is_open - 是否打开：1-打开；0-不打开，默认为1
     */
    public Integer getIsOpen() {
        return isOpen;
    }

    /**
     * 设置是否打开：1-打开；0-不打开，默认为1
     *
     * @param isOpen 是否打开：1-打开；0-不打开，默认为1
     */
    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 获取状态：y-启用；n-禁用，默认为y
     *
     * @return status - 状态：y-启用；n-禁用，默认为y
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：y-启用；n-禁用，默认为y
     *
     * @param status 状态：y-启用；n-禁用，默认为y
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysMenuDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", detail='" + detail + '\'' +
                ", isMenu=" + isMenu +
                ", levels=" + levels +
                ", pcode='" + pcode + '\'' +
                ", pcodes='" + pcodes + '\'' +
                ", isOpen=" + isOpen +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}