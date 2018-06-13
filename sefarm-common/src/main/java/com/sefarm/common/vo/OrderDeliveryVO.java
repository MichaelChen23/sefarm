package com.sefarm.common.vo;

import java.io.Serializable;

/**
 * 分页显示 订单配送 VO类
 *
 * @author mc
 * @date 2018-4-17
 */
public class OrderDeliveryVO implements Serializable {

    /**
     * 订单配送表ID
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 系统部门id
     */
    private Long sysDeptId;

    /**
     * 系统部门名
     */
    private String sysDeptName;

    /**
     * 系统用户id
     */
    private Long sysUserId;

    /**
     * 系统用户名
     */
    private String sysUserName;

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
    private String expressName;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 订单配送状态：new-新增；check-审核中；ready-待发货；delivery-已发货；receive-已签收；cancel-已取消；默认：new-新增
     */
    private String status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新状态人
     */
    private String updateBy;

    /**
     * 状态更新时间
     */
    private String updateTime;

    /**
     * 发货时间
     */
    private String deliveryTime;

    /**
     * 收货时间
     */
    private String receiveTime;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getSysDeptId() {
        return sysDeptId;
    }

    public void setSysDeptId(Long sysDeptId) {
        this.sysDeptId = sysDeptId;
    }

    public String getSysDeptName() {
        return sysDeptName;
    }

    public void setSysDeptName(String sysDeptName) {
        this.sysDeptName = sysDeptName;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OrderDeliveryVO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", sysDeptId=" + sysDeptId +
                ", sysDeptName='" + sysDeptName + '\'' +
                ", sysUserId=" + sysUserId +
                ", sysUserName='" + sysUserName + '\'' +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", expressName='" + expressName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
