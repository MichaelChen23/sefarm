package com.sefarm.model.order;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单配送的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_order_delivery")
public class OrderDeliveryDO extends BaseDO implements Serializable {
    /**
     * 订单配送表ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 收货人名字
     */
    private String receiver;

    /**
     * 收货人地址
     */
    private String address;

    /**
     * 收货人电话
     */
    private String mobile;

    /**
     * 快递名字：yt-圆通；sto-申通；
     */
    @Column(name = "express_name")
    private String expressName;

    /**
     * 快递单号
     */
    @Column(name = "express_no")
    private String expressNo;

    /**
     * 跟单员/操作人账号
     */
    @Column(name = "handler_account")
    private String handlerAccount;

    /**
     * 订单状态：init-未审核；pass-已审核；delivery -已发货；sign-已签收；cancel-已取消；file-已归档；
     */
    private String status;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 收货时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取订单配送表ID
     *
     * @return id - 订单配送表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单配送表ID
     *
     * @param id 订单配送表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取收货人名字
     *
     * @return receiver - 收货人名字
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置收货人名字
     *
     * @param receiver 收货人名字
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取收货人地址
     *
     * @return address - 收货人地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货人地址
     *
     * @param address 收货人地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取收货人电话
     *
     * @return mobile - 收货人电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置收货人电话
     *
     * @param mobile 收货人电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取快递名字：yt-圆通；sto-申通；
     *
     * @return express_name - 快递名字：yt-圆通；sto-申通；
     */
    public String getExpressName() {
        return expressName;
    }

    /**
     * 设置快递名字：yt-圆通；sto-申通；
     *
     * @param expressName 快递名字：yt-圆通；sto-申通；
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    /**
     * 获取快递单号
     *
     * @return express_no - 快递单号
     */
    public String getExpressNo() {
        return expressNo;
    }

    /**
     * 设置快递单号
     *
     * @param expressNo 快递单号
     */
    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    /**
     * 获取跟单员/操作人账号
     *
     * @return handler_account - 跟单员/操作人账号
     */
    public String getHandlerAccount() {
        return handlerAccount;
    }

    /**
     * 设置跟单员/操作人账号
     *
     * @param handlerAccount 跟单员/操作人账号
     */
    public void setHandlerAccount(String handlerAccount) {
        this.handlerAccount = handlerAccount;
    }

    /**
     * 获取订单状态：init-未审核；pass-已审核；delivery -已发货；sign-已签收；cancel-已取消；file-已归档；
     *
     * @return status - 订单状态：init-未审核；pass-已审核；delivery -已发货；sign-已签收；cancel-已取消；file-已归档；
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态：init-未审核；pass-已审核；delivery -已发货；sign-已签收；cancel-已取消；file-已归档；
     *
     * @param status 订单状态：init-未审核；pass-已审核；delivery -已发货；sign-已签收；cancel-已取消；file-已归档；
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliveryTime 发货时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取收货时间
     *
     * @return receive_time - 收货时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置收货时间
     *
     * @param receiveTime 收货时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
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

    @Override
    public String toString() {
        return "OrderDeliveryDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", expressName='" + expressName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", handlerAccount='" + handlerAccount + '\'' +
                ", status='" + status + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", receiveTime=" + receiveTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}