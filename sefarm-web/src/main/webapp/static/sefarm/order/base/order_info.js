/**
 * 订单 详情对话框（可用于添加和修改对话框）
 */
var OrderInfoDlg = {
    orderInfoData: {}
};

/**
 * 清除数据
 */
OrderInfoDlg.clearData = function () {
    this.orderInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderInfoDlg.set = function (key, val) {
    this.orderInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrderInfoDlg.close = function () {
    parent.layer.close(window.parent.Order.layerIndex);
};

/**
 * 收集数据
 */
OrderInfoDlg.collectData = function() {
    this.set('id').set('orderNo').set('payType').set('carryFeeTotal').set('productTotal').set('exchangeScore').set('remark')
        .set('createTime').set('account').set('carry').set('quantity').set('amount').set('requirement').set('status');
};

/**
 * 提交添加 订单
 */
OrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/order/save", function(data){
        Feng.success("添加成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
};

/**
 * 提交修改 订单
 */
OrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/order/update", function(data){
        Feng.success("修改成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());
    //初始化时间并转换格式
    $("#createTime").val(cstTimeFormatter($("#createTimeValue").val()));
});

