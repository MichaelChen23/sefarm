/**
 * 系统角色详情对话框（可用于添加和修改对话框）
 */
var SysRoleInfoDlg = {
    sysRoleInfoData: {},
    deptZtree : null,
    pNameZtree : null
};

/**
 * 清除数据
 */
SysRoleInfoDlg.clearData = function () {
    this.sysRoleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysRoleInfoDlg.set = function (key, val) {
    this.sysRoleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysRoleInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
SysRoleInfoDlg.close = function () {
    parent.layer.close(window.parent.SysRole.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
SysRoleInfoDlg.onClickDept = function(e, treeId, treeNode) {
    $("#deptName").attr("value", SysRoleInfoDlg.deptZtree.getSelectedVal());
    $("#sysDeptId").attr("value", treeNode.id);
};

/**
 * 点击上级角色input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
SysRoleInfoDlg.onClickPName = function(e, treeId, treeNode) {
    $("#pName").attr("value", SysRoleInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
SysRoleInfoDlg.showDeptNameSelectTree = function() {
    Feng.showInputTree("deptName","deptContent");
};

/**
 * 显示上级角色选择的树
 *
 * @returns
 */
SysRoleInfoDlg.showPNameSelectTree = function() {
    Feng.showInputTree("pName","pNameContent");
};

/**
 * 收集数据
 */
SysRoleInfoDlg.collectData = function() {
    this.set('id').set('name').set('code').set('sort').set('pid').set('sysDeptId').set('status').set('remark');
};

/**
 * 提交添加系统角色
 */
SysRoleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys-role/save", function(data){
        Feng.success("添加成功!");
        window.parent.SysRole.table.refresh();
        SysRoleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysRoleInfoData);
    ajax.start();
};

/**
 * 提交修改系统角色
 */
SysRoleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys-role/update", function(data){
        Feng.success("修改成功!");
        window.parent.SysRole.table.refresh();
        SysRoleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysRoleInfoData);
    ajax.start();
};

$(function() {
    var deptTree = new $ZTree("deptTree", "/sys-dept/getDeptTree");
    deptTree.bindOnClick(SysRoleInfoDlg.onClickDept);
    deptTree.init();
    SysRoleInfoDlg.deptZtree = deptTree;

    var pNameTree = new $ZTree("pNameTree", "/sys-role/sysRoleTree");
    pNameTree.bindOnClick(SysRoleInfoDlg.onClickPName);
    pNameTree.init();
    SysRoleInfoDlg.pNameZtree = pNameTree;

    //初始化状态选项
    $("#status").val($("#statusValue").val());
});

