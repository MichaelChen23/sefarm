package com.sefarm.common.vo;

import java.io.Serializable;

/**
 * 分页显示 产品评论 VO类
 *
 * @author mc
 * @date 2018-4-17
 */
public class ProductCommentVO implements Serializable {

    /**
     * 产品评论ID
     */
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论星级：1，2，3，4，5，默认为0
     */
    private Integer star;

    /**
     * 状态：y-显示；n-不显示，默认为y
     */
    private String status;

    /**
     * 用户评论时间
     */
    private String createTime;

    /**
     * 商家回复人
     */
    private String replier;

    /**
     * 商家回复用户评论
     */
    private String reply;

    /**
     * 商家评论回复时间
     */
    private String replyTime;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
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

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ProductCommentVO{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", content='" + content + '\'' +
                ", star=" + star +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", replier='" + replier + '\'' +
                ", reply='" + reply + '\'' +
                ", replyTime='" + replyTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
