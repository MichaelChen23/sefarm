/**
 * 产品类型 详情对话框（可用于添加和修改对话框）
 */
var ProdTypeInfoDlg = {
    prodTypeInfoData: {}
};

/**
 * 清除数据
 */
ProdTypeInfoDlg.clearData = function () {
    this.prodTypeInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProdTypeInfoDlg.set = function (key, val) {
    this.prodTypeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProdTypeInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ProdTypeInfoDlg.close = function () {
    parent.layer.close(window.parent.ProdType.layerIndex);
};

/**
 * 收集数据
 */
ProdTypeInfoDlg.collectData = function() {
    this.set('id').set('name').set('productCatalogId').set('image').set('sort').set('detail').set('status');
};

/**
 * 提交添加 产品类型
 */
ProdTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod-type/save", function(data){
        Feng.success("添加成功!");
        window.parent.ProdType.table.refresh();
        ProdTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.prodTypeInfoData);
    ajax.start();
};

/**
 * 提交修改 产品类型
 */
ProdTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod-type/update", function(data){
        Feng.success("修改成功!");
        window.parent.ProdType.table.refresh();
        ProdTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.prodTypeInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());

    // 初始化图片上传
    var imageUp = new $WebUpload("image");
    imageUp.init();

    //获取所有的产品目录
    $.ajax({
        url: '/api/prod-cata/getAll',
        async: false,
        type: "GET",
        data: {},
        success: function (data, status) {
            if (data.code == 200) {
                $.each(data.result, function (key, value) {
                    var option = $("<option>").val(value.id).text(value.name);
                    $("#productCatalogId").append(option);
                });
                //初始化 产品目录id
                $("#productCatalogId").find("option[value=" + $("#productCatalogIdValue").val() + "]").attr("selected", true);
            }
        }
    });

});

