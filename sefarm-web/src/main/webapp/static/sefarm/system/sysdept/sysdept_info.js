/**
 * 系统部门详情对话框（可用于添加和修改对话框）
 */
var SysDeptInfoDlg = {
    sysDeptInfoData: {},
    pNameZtree : null
};

/**
 * 清除数据
 */
SysDeptInfoDlg.clearData = function () {
    this.sysDeptInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysDeptInfoDlg.set = function (key, val) {
    this.sysDeptInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysDeptInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
SysDeptInfoDlg.close = function () {
    parent.layer.close(window.parent.SysDept.layerIndex);
};

/**
 * 点击上级部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
SysDeptInfoDlg.onClickPName = function(e, treeId, treeNode) {
    $("#pName").attr("value", SysDeptInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示上级部门选择的树
 *
 * @returns
 */
SysDeptInfoDlg.showPNameSelectTree = function() {
    Feng.showInputTree("pName","pNameContent");
};

/**
 * 收集数据
 */
SysDeptInfoDlg.collectData = function() {
    this.set('id').set('name').set('fullName').set('sort').set('description').set('pid').set('status');
};

/**
 * 提交添加系统部门
 */
SysDeptInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys-dept/save", function(data){
        Feng.success("添加成功!");
        window.parent.SysDept.table.refresh();
        SysDeptInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysDeptInfoData);
    ajax.start();
};

/**
 * 提交修改系统部门
 */
SysDeptInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys-dept/update", function(data){
        Feng.success("修改成功!");
        window.parent.SysDept.table.refresh();
        SysDeptInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysDeptInfoData);
    ajax.start();
};

$(function() {

    var pNameTree = new $ZTree("pNameTree", "/sys-dept/getDeptTree");
    pNameTree.bindOnClick(SysDeptInfoDlg.onClickPName);
    pNameTree.init();
    SysDeptInfoDlg.pNameZtree = pNameTree;

    //初始化状态选项
    $("#status").val($("#statusValue").val());
});

