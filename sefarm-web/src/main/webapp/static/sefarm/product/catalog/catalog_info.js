/**
 * 产品目录 详情对话框（可用于添加和修改对话框）
 */
var ProdCatalogInfoDlg = {
    prodCatalogInfoData: {}
};

/**
 * 清除数据
 */
ProdCatalogInfoDlg.clearData = function () {
    this.prodCatalogInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProdCatalogInfoDlg.set = function (key, val) {
    this.prodCatalogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProdCatalogInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ProdCatalogInfoDlg.close = function () {
    parent.layer.close(window.parent.ProdCatalog.layerIndex);
};

/**
 * 收集数据
 */
ProdCatalogInfoDlg.collectData = function() {
    this.set('id').set('name').set('sort').set('detail').set('status');
};

/**
 * 提交添加 产品目录
 */
ProdCatalogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod-cata/save", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.ProdCatalog.table.refresh();
        ProdCatalogInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
    });
    ajax.set(this.prodCatalogInfoData);
    ajax.start();
};

/**
 * 提交修改 产品目录
 */
ProdCatalogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod-cata/update", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        window.parent.ProdCatalog.table.refresh();
        ProdCatalogInfoDlg.close();
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.prodCatalogInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());
});

