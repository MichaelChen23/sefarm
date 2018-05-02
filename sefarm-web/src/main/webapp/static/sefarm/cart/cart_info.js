/**
 * 购物车 详情对话框（可用于添加和修改对话框）
 */
var CartInfoDlg = {
    cartInfoData: {}
};

/**
 * 清除数据
 */
CartInfoDlg.clearData = function () {
    this.cartInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CartInfoDlg.set = function (key, val) {
    this.cartInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CartInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
CartInfoDlg.close = function () {
    parent.layer.close(window.parent.Cart.layerIndex);
};

/**
 * 收集数据
 */
CartInfoDlg.collectData = function() {
    this.set('id').set('account').set('productId').set('number').set('createTime').set('updateTime');
};

/**
 * 提交添加 购物车
 */
CartInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/cart/saveCart", function(data){
        Feng.success("添加成功!");
        window.parent.Cart.table.refresh();
        CartInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cartInfoData);
    ajax.start();
};

/**
 * 提交修改 购物车
 */
CartInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/cart/updateCart", function(data){
        Feng.success("修改成功!");
        if(window.parent.Cart != undefined) {
            window.parent.Cart.table.refresh();
            CartInfoDlg.close();
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cartInfoData);
    ajax.start();
};

$(function() {

});

