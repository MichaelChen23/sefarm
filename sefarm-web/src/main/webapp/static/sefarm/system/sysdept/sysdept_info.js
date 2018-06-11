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
    $("#pNameSelect").attr("value", SysDeptInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示上级部门选择的树
 *
 * @returns
 */
SysDeptInfoDlg.showPNameSelectTree = function() {
    // pNameSelect为上级选择框的id
    var pNameObj = $("#pNameSelect");
    var pNameOffset = $("#pNameSelect").offset();
    //pNameContent为上级弹出框的id
    $("#pNameContent").css({
        left: pNameOffset.left + "px",
        top: pNameOffset.top + pNameObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onpNameBodyDown);
};

/**
 * 隐藏上级部门选择的树
 */
SysDeptInfoDlg.hidepNameSelectTree = function () {
    //pNameContent为上级部门弹出框的id
    $("#pNameContent").fadeOut("fast");
    $("body").unbind("mousedown", onpNameBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

function onpNameBodyDown(event) {
    //menuContent为上级部门弹出框pNameContent的class样式
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        SysDeptInfoDlg.hidepNameSelectTree();
    }
};

/**
 * 收集数据
 */
SysDeptInfoDlg.collectData = function() {
    this.set('id').set('name').set('fullName').set('sort').set('detail').set('pid').set('status');
};

/**
 * 提交添加系统部门
 */
SysDeptInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/sys-dept/save", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.SysDept.table.refresh();
        SysDeptInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
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
    var ajax = new $ax(Feng.ctxPath + "/api/sys-dept/update", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        window.parent.SysDept.table.refresh();
        SysDeptInfoDlg.close();
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.sysDeptInfoData);
    ajax.start();
};

$(function() {

    var pNameTree = new $ZTree("pNameTree", "/api/sys-dept/getDeptTree");
    pNameTree.bindOnClick(SysDeptInfoDlg.onClickPName);
    pNameTree.init();
    SysDeptInfoDlg.pNameZtree = pNameTree;

    //初始化状态选项
    $("#status").val($("#statusValue").val());
});

