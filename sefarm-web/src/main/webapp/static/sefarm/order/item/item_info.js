/**
 * 订单项 详情对话框（可用于添加和修改对话框）
 */
var OrderItemInfoDlg = {
    orderItemInfoData: {}
};

/**
 * 清除数据
 */
OrderItemInfoDlg.clearData = function () {
    this.orderItemInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderItemInfoDlg.set = function (key, val) {
    this.orderItemInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderItemInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrderItemInfoDlg.close = function () {
    parent.layer.close(window.parent.OrderItem.layerIndex);
};

/**
 * 收集数据
 */
OrderItemInfoDlg.collectData = function() {
    this.set('id').set('orderId').set('productId').set('productName').set('price').set('number').set('unit')
        .set('carryFee').set('total').set('commentFlag').set('remark').set('account').set('createTime');
};

/**
 * 提交添加 订单项
 */
OrderItemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order-item/save", function(data){
        Feng.success("添加成功!");
        window.parent.OrderItem.table.refresh();
        OrderItemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderItemInfoData);
    ajax.start();
};

/**
 * 提交修改 订单项
 */
OrderItemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order-item/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderItem.table.refresh();
        OrderItemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderItemInfoData);
    ajax.start();
};

$(function() {

    //初始化 是否评论 选项
    $("#commentFlag").val($("#commentFlagValue").val());
});

