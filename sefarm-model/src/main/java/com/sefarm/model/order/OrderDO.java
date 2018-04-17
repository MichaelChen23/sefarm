package com.sefarm.model.order;

import com.sefarm.common.base.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_order")
public class OrderDO extends BaseDO implements Serializable {
    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订单编号,唯一
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 购买产品的用户账号
     */
    private String account;

    /**
     * 付款方式：alipay-支付宝；wechat-微信支付；cash-现金
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 运送方式：express-邮递； EMS-邮政
     */
    private String carry;

    /**
     * 运送总费用：默认为0-包邮
     */
    @Column(name = "carry_fee_total")
    private BigDecimal carryFeeTotal;

    /**
     * 产品总数量：默认为0
     */
    private Integer quantity;

    /**
     * 产品总金额：默认为0
     */
    @Column(name = "product_total")
    private BigDecimal productTotal;

    /**
     * 订单总金额：产品总金额+运送总费用，默认为0
     */
    private BigDecimal amount;

    /**
     * 订单总兑换积分：默认为0
     */
    @Column(name = "exchange_score")
    private BigDecimal exchangeScore;

    /**
     * 客户的附加要求备注
     */
    private String requirement;

    /**
     * 后台管理人员的备注，备注些修改信息，发货确认信息等
     */
    private String remark;

    /**
     * 订单状态：y-完成，n-未完成，默认n
     */
    private String status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
     * 获取订单ID
     *
     * @return id - 订单ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单ID
     *
     * @param id 订单ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号,唯一
     *
     * @return order_no - 订单编号,唯一
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号,唯一
     *
     * @param orderNo 订单编号,唯一
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取购买产品的用户账号
     *
     * @return account - 购买产品的用户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置购买产品的用户账号
     *
     * @param account 购买产品的用户账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取付款方式：alipay-支付宝；wechat-微信支付；cash-现金
     *
     * @return pay_type - 付款方式：alipay-支付宝；wechat-微信支付；cash-现金
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置付款方式：alipay-支付宝；wechat-微信支付；cash-现金
     *
     * @param payType 付款方式：alipay-支付宝；wechat-微信支付；cash-现金
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取运送方式：express-邮递； EMS-邮政
     *
     * @return carry - 运送方式：express-邮递； EMS-邮政
     */
    public String getCarry() {
        return carry;
    }

    /**
     * 设置运送方式：express-邮递； EMS-邮政
     *
     * @param carry 运送方式：express-邮递； EMS-邮政
     */
    public void setCarry(String carry) {
        this.carry = carry;
    }

    /**
     * 获取运送总费用：默认为0-包邮
     *
     * @return carry_fee_total - 运送总费用：默认为0-包邮
     */
    public BigDecimal getCarryFeeTotal() {
        return carryFeeTotal;
    }

    /**
     * 设置运送总费用：默认为0-包邮
     *
     * @param carryFeeTotal 运送总费用：默认为0-包邮
     */
    public void setCarryFeeTotal(BigDecimal carryFeeTotal) {
        this.carryFeeTotal = carryFeeTotal;
    }

    /**
     * 获取产品总数量：默认为0
     *
     * @return quantity - 产品总数量：默认为0
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置产品总数量：默认为0
     *
     * @param quantity 产品总数量：默认为0
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取产品总金额：默认为0
     *
     * @return product_total - 产品总金额：默认为0
     */
    public BigDecimal getProductTotal() {
        return productTotal;
    }

    /**
     * 设置产品总金额：默认为0
     *
     * @param productTotal 产品总金额：默认为0
     */
    public void setProductTotal(BigDecimal productTotal) {
        this.productTotal = productTotal;
    }

    /**
     * 获取订单总金额：产品总金额+运送总费用，默认为0
     *
     * @return amount - 订单总金额：产品总金额+运送总费用，默认为0
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置订单总金额：产品总金额+运送总费用，默认为0
     *
     * @param amount 订单总金额：产品总金额+运送总费用，默认为0
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取订单总兑换积分：默认为0
     *
     * @return exchange_score - 订单总兑换积分：默认为0
     */
    public BigDecimal getExchangeScore() {
        return exchangeScore;
    }

    /**
     * 设置订单总兑换积分：默认为0
     *
     * @param exchangeScore 订单总兑换积分：默认为0
     */
    public void setExchangeScore(BigDecimal exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    /**
     * 获取客户的附加要求备注
     *
     * @return requirement - 客户的附加要求备注
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * 设置客户的附加要求备注
     *
     * @param requirement 客户的附加要求备注
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * 获取后台管理人员的备注，备注些修改信息，发货确认信息等
     *
     * @return remark - 后台管理人员的备注，备注些修改信息，发货确认信息等
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置后台管理人员的备注，备注些修改信息，发货确认信息等
     *
     * @param remark 后台管理人员的备注，备注些修改信息，发货确认信息等
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取订单状态：y-完成，n-未完成，默认n
     *
     * @return status - 订单状态：y-完成，n-未完成，默认n
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态：y-完成，n-未完成，默认n
     *
     * @param status 订单状态：y-完成，n-未完成，默认n
     */
    public void setStatus(String status) {
        this.status = status;
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
        return "OrderDO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", account='" + account + '\'' +
                ", payType='" + payType + '\'' +
                ", carry='" + carry + '\'' +
                ", carryFeeTotal=" + carryFeeTotal +
                ", quantity=" + quantity +
                ", productTotal=" + productTotal +
                ", amount=" + amount +
                ", exchangeScore=" + exchangeScore +
                ", requirement='" + requirement + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}