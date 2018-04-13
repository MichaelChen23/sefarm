package com.sefarm.common.vo;

import java.io.Serializable;

/**
 * 系统菜单树列表VO类，菜单管理list
 *
 * @author mc
 * @date 2018-4-13
 */
public class SysMenuTreeVO implements Serializable {

    /**
     * 系统菜单ID
     */
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
     * 描述
     */
    private String description;

    /**
     * 是否菜单：1-是；0-不是
     */
    private String isMenu;

    /**
     * 菜单层级，最高层是1
     */
    private Integer levels;

    /**
     * 上级菜单编号
     */
    private String pcode;

    /**
     * 上级菜单编号
     */
    private String pName;

    /**
     * 所有上级菜单编号集合，用‘,’隔开
     */
    private String pcodes;

    /**
     * 是否打开：1-打开；0-不打开，默认为1
     */
    private String isOpen;

    /**
     * 状态
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPcodes() {
        return pcodes;
    }

    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysMenuTreeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", description='" + description + '\'' +
                ", isMenu='" + isMenu + '\'' +
                ", levels=" + levels +
                ", pcode='" + pcode + '\'' +
                ", pName='" + pName + '\'' +
                ", pcodes='" + pcodes + '\'' +
                ", isOpen='" + isOpen + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
