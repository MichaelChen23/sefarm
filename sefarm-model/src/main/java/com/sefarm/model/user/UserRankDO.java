package com.sefarm.model.user;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 用户等级的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_user_rank")
public class UserRankDO extends BaseDO implements Serializable {
    /**
     * 用户等级ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 用户等级编码
     */
    private String code;

    /**
     * 级别名称
     */
    private String name;

    /**
     * 最小积分，默认为0
     */
    @Column(name = "min_score")
    private BigDecimal minScore;

    /**
     * 最大积分，默认为0
     */
    @Column(name = "max_score")
    private BigDecimal maxScore;

    /**
     * 备注
     */
    private String remark;

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
     * 获取用户等级ID
     *
     * @return id - 用户等级ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户等级ID
     *
     * @param id 用户等级ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户等级编码
     *
     * @return code - 用户等级编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置用户等级编码
     *
     * @param code 用户等级编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取级别名称
     *
     * @return name - 级别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置级别名称
     *
     * @param name 级别名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取最小积分，默认为0
     *
     * @return min_score - 最小积分，默认为0
     */
    public BigDecimal getMinScore() {
        return minScore;
    }

    /**
     * 设置最小积分，默认为0
     *
     * @param minScore 最小积分，默认为0
     */
    public void setMinScore(BigDecimal minScore) {
        this.minScore = minScore;
    }

    /**
     * 获取最大积分，默认为0
     *
     * @return max_score - 最大积分，默认为0
     */
    public BigDecimal getMaxScore() {
        return maxScore;
    }

    /**
     * 设置最大积分，默认为0
     *
     * @param maxScore 最大积分，默认为0
     */
    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
        return "UserRankDO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", minScore=" + minScore +
                ", maxScore=" + maxScore +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}