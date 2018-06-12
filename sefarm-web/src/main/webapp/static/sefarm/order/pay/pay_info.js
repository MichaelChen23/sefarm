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
    this.set('id').set('orderId').set('account').set('payAmount').set('createTime').set('payType').set('openid').set('appId').set('timeStamp').set('nonceStr').set('prepayId').set('signType')
        .set('paySign').set('mchId').set('deviceInfo').set('tradeType').set('bankType').set('feeType').set('subscribeFlag').set('transactionId').set('payStatus').set('errCode').set('errCodeDes').set('payIp');
};

/**
 * 提交添加 订单支付记录
 */
OrderPayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/order-pay/save", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.OrderPay.table.refresh();
        OrderPayInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
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
    var ajax = new $ax(Feng.ctxPath + "/api/order-pay/update", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        window.parent.OrderPay.table.refresh();
        OrderPayInfoDlg.close();
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.orderPayInfoData);
    ajax.start();
};

$(function() {

});

