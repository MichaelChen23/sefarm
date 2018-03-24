package com.sefarm.model.product;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 产品评论的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_product_comment")
public class ProductCommentDO extends BaseDO implements Serializable {
    /**
     * 产品评论ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Long productId;

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
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论星级：1，2，3，4，5，默认为0
     */
    private Boolean star;

    /**
     * 状态：y-显示；n-不显示，默认为y
     */
    private String status;

    /**
     * 用户评论时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
    @Column(name = "reply_time")
    private Date replyTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取产品评论ID
     *
     * @return id - 产品评论ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置产品评论ID
     *
     * @param id 产品评论ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取用户帐号
     *
     * @return account - 用户帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户帐号
     *
     * @param account 用户帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户名称
     *
     * @return name - 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称
     *
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论星级：1，2，3，4，5，默认为0
     *
     * @return star - 评论星级：1，2，3，4，5，默认为0
     */
    public Boolean getStar() {
        return star;
    }

    /**
     * 设置评论星级：1，2，3，4，5，默认为0
     *
     * @param star 评论星级：1，2，3，4，5，默认为0
     */
    public void setStar(Boolean star) {
        this.star = star;
    }

    /**
     * 获取状态：y-显示；n-不显示，默认为y
     *
     * @return status - 状态：y-显示；n-不显示，默认为y
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：y-显示；n-不显示，默认为y
     *
     * @param status 状态：y-显示；n-不显示，默认为y
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取用户评论时间
     *
     * @return create_time - 用户评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置用户评论时间
     *
     * @param createTime 用户评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取商家回复人
     *
     * @return replier - 商家回复人
     */
    public String getReplier() {
        return replier;
    }

    /**
     * 设置商家回复人
     *
     * @param replier 商家回复人
     */
    public void setReplier(String replier) {
        this.replier = replier;
    }

    /**
     * 获取商家回复用户评论
     *
     * @return reply - 商家回复用户评论
     */
    public String getReply() {
        return reply;
    }

    /**
     * 设置商家回复用户评论
     *
     * @param reply 商家回复用户评论
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * 获取商家评论回复时间
     *
     * @return reply_time - 商家评论回复时间
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * 设置商家评论回复时间
     *
     * @param replyTime 商家评论回复时间
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
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
        return "ProductCommentDO{" +
                "id=" + id +
                ", productId=" + productId +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", content='" + content + '\'' +
                ", star=" + star +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", replier='" + replier + '\'' +
                ", reply='" + reply + '\'' +
                ", replyTime=" + replyTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}