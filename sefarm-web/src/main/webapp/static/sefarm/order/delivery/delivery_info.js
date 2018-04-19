/**
 * 订单配送 详情对话框（可用于添加和修改对话框）
 */
var OrderDeliveryInfoDlg = {
    orderDeliveryInfoData: {}
};

/**
 * 清除数据
 */
OrderDeliveryInfoDlg.clearData = function () {
    this.orderDeliveryInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderDeliveryInfoDlg.set = function (key, val) {
    this.orderDeliveryInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderDeliveryInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrderDeliveryInfoDlg.close = function () {
    parent.layer.close(window.parent.OrderDelivery.layerIndex);
};

/**
 * 收集数据
 */
OrderDeliveryInfoDlg.collectData = function() {
    this.set('id').set('orderId').set('sysDeptId').set('sysUserId').set('receiver').set('address').set('mobile').set('expressName')
        .set('expressNo').set('status').set('createTime').set('updateBy').set('updateTime').set('deliveryTime').set('receiveTime').set('remark');
};

/**
 * 提交添加 订单配送
 */
OrderDeliveryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order-dely/save", function(data){
        Feng.success("添加成功!");
        window.parent.OrderDelivery.table.refresh();
        OrderDeliveryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderDeliveryInfoData);
    ajax.start();
};

/**
 * 提交修改 订单配送
 */
OrderDeliveryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order-dely/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderDelivery.table.refresh();
        OrderDeliveryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderDeliveryInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());

});

