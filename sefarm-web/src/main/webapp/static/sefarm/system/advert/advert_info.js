/**
 * 广告 详情对话框（可用于添加和修改对话框）
 */
var AdvertInfoDlg = {
    advertInfoData: {}
};

/**
 * 清除数据
 */
AdvertInfoDlg.clearData = function () {
    this.advertInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdvertInfoDlg.set = function (key, val) {
    this.advertInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdvertInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
AdvertInfoDlg.close = function () {
    parent.layer.close(window.parent.Advert.layerIndex);
};

/**
 * 收集数据
 */
AdvertInfoDlg.collectData = function() {
    this.set('id').set('title').set('image').set('content').set('url').set('productId').set('status')
        .set('startTime').set('endTime').set('sort').set('type').set('remark').set('html');
};

/**
 * 提交添加 广告
 */
AdvertInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/advert/save", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.Advert.table.refresh();
        AdvertInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
    });
    ajax.set(this.advertInfoData);
    ajax.start();
};

/**
 * 提交修改 广告
 */
AdvertInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/advert/update", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        if(window.parent.Advert != undefined) {
            window.parent.Advert.table.refresh();
            AdvertInfoDlg.close();
        }
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.advertInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());
    //初始化时间并转换格式
    $("#startTime").val(cstTimeFormatter($("#startTimeValue").val()));
    $("#endTime").val(cstTimeFormatter($("#endTimeValue").val()));

    // 初始化图片上传
    var imageUp = new $WebUpload("image");
    imageUp.init();
});

