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
    this.set('id').set('orderId').set('productId').set('productName').set('productImage').set('price').set('number').set('unit')
        .set('carryFee').set('total').set('commentFlag').set('remark').set('account').set('createTime');
};

/**
 * 提交添加 订单项
 */
OrderItemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/order-item/save", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.OrderItem.table.refresh();
        OrderItemInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
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
    var ajax = new $ax(Feng.ctxPath + "/api/order-item/update", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        window.parent.OrderItem.table.refresh();
        OrderItemInfoDlg.close();
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.orderItemInfoData);
    ajax.start();
};

$(function() {

    //初始化 是否评论 选项
    $("#commentFlag").val($("#commentFlagValue").val());
});

