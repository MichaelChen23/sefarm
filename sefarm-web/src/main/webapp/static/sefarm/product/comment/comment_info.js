/**
 * 产品评论 详情对话框（可用于添加和修改对话框）
 */
var ProductCommentInfoDlg = {
    productCommentInfoData: {}
};

/**
 * 清除数据
 */
ProductCommentInfoDlg.clearData = function () {
    this.productCommentInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductCommentInfoDlg.set = function (key, val) {
    this.productCommentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductCommentInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ProductCommentInfoDlg.close = function () {
    parent.layer.close(window.parent.ProductComment.layerIndex);
};

/**
 * 收集数据
 */
ProductCommentInfoDlg.collectData = function() {
    this.set('id').set('productId').set('account').set('name').set('orderId').set('content')
        .set('star').set('remark').set('status').set('replier').set('reply').set('replyTime');
};

/**
 * 提交添加 产品评论
 */
ProductCommentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod-comment/save", function(data){
        Feng.success("添加成功!");
        window.parent.ProductComment.table.refresh();
        ProductCommentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productCommentInfoData);
    ajax.start();
};

/**
 * 提交修改 产品评论
 */
ProductCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod-comment/update", function(data){
        Feng.success("修改成功!");
        window.parent.ProductComment.table.refresh();
        ProductCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productCommentInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());

});

