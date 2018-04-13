/**
 * 系统菜单详情对话框（可用于添加和修改对话框）
 */
var SysMenuInfoDlg = {
    sysMenuInfoData: {},
    pNameZtree : null
};

/**
 * 清除数据
 */
SysMenuInfoDlg.clearData = function () {
    this.sysMenuInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysMenuInfoDlg.set = function (key, val) {
    this.sysMenuInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysMenuInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
SysMenuInfoDlg.close = function () {
    parent.layer.close(window.parent.SysMenu.layerIndex);
};

/**
 * 点击上级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
SysMenuInfoDlg.onClickPName = function(e, treeId, treeNode) {
    $("#pName").attr("value", SysMenuInfoDlg.pNameZtree.getSelectedVal());
    $("#pcode").attr("value", treeNode.id);
};

/**
 * 显示上级菜单选择的树
 *
 * @returns
 */
SysMenuInfoDlg.showPNameSelectTree = function() {
    Feng.showInputTree("pName","pNameContent");
};

/**
 * 收集数据
 */
SysMenuInfoDlg.collectData = function() {
    this.set('id').set('name').set('code').set('icon').set('url').set('sort').set('description').set('isMenu').set('levels')
        .set('pcode').set('isOpen').set('status');
};

/**
 * 提交添加系统菜单
 */
SysMenuInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys-menu/save", function(data){
        Feng.success("添加成功!");
        window.parent.SysMenu.table.refresh();
        SysMenuInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysMenuInfoData);
    ajax.start();
};

/**
 * 提交修改系统菜单
 */
SysMenuInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sys-menu/update", function(data){
        Feng.success("修改成功!");
        window.parent.SysMenu.table.refresh();
        SysMenuInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysMenuInfoData);
    ajax.start();
};

$(function() {

    var pNameTree = new $ZTree("pNameTree", "/sys-menu/getAllMenuTree");
    pNameTree.bindOnClick(SysMenuInfoDlg.onClickPName);
    pNameTree.init();
    SysMenuInfoDlg.pNameZtree = pNameTree;

    //初始化状态选项
    $("#status").val($("#statusValue").val());
    $("#isMenu").val($("#isMenuValue").val());
    $("#isOpen").val($("#isOpenValue").val());
});

