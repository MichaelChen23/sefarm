/**
 * 订单支付记录 详情对话框（可用于添加和修改对话框）
 */
var OrderPayInfoDlg = {
    orderPayInfoData: {}
};

/**
 * 清除数据
 */
OrderPayInfoDlg.clearData = function () {
    this.orderPayInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderPayInfoDlg.set = function (key, val) {
    this.orderPayInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderPayInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrderPayInfoDlg.close = function () {
    parent.layer.close(window.parent.OrderPay.layerIndex);
};

/**
 * 收集数据
 */
OrderPayInfoDlg.collectData = function() {
    this.set('id').set('orderId').set('account').set('payAmount').set('payTime').set('payType')
        .set('payAccount').set('payTradeNo').set('payStatus');
};

/**
 * 提交添加 订单支付记录
 */
OrderPayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order-pay/save", function(data){
        Feng.success("添加成功!");
        window.parent.OrderPay.table.refresh();
        OrderPayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderPayInfoData);
    ajax.start();
};

/**
 * 提交修改 订单支付记录
 */
OrderPayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order-pay/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderPay.table.refresh();
        OrderPayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderPayInfoData);
    ajax.start();
};

$(function() {

});

