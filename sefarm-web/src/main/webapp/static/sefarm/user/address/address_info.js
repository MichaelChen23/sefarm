/**
 * 用户地址 详情对话框（可用于添加和修改对话框）
 */
var AddressInfoDlg = {
    addressInfoData: {}
};

/**
 * 清除数据
 */
AddressInfoDlg.clearData = function () {
    this.addressInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AddressInfoDlg.set = function (key, val) {
    this.addressInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AddressInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
AddressInfoDlg.close = function () {
    parent.layer.close(window.parent.Address.layerIndex);
};

/**
 * 收集数据
 */
AddressInfoDlg.collectData = function() {
    this.set('id').set('account').set('name').set('provinceId').set('province').set('cityId').set('city').set('areaId').set('area').set('address')
        .set('zip').set('phone').set('mobile').set('defaultFlag').set('createTime').set('updateBy').set('updateTime');
};

/**
 * 提交添加 用户地址
 */
AddressInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/user-adr/saveAddress", function(data){
        Feng.success("添加成功!");
        window.parent.Address.table.refresh();
        AddressInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.addressInfoData);
    ajax.start();
};

/**
 * 提交修改 用户地址
 */
AddressInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/user-adr/updateAddress", function(data){
        Feng.success("修改成功!");
        if(window.parent.Address != undefined) {
            window.parent.Address.table.refresh();
            AddressInfoDlg.close();
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.addressInfoData);
    ajax.start();
};

$(function() {

    //初始化是否默认地址选项
    $("#defaultFlag").val($("#defaultFlagValue").val());
    //初始化时间并转换格式
    $("#createTime").val(cstTimeFormatter($("#createTimeValue").val()));
    $("#updateTime").val(cstTimeFormatter($("#updateTimeValue").val()));
});

