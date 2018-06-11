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
    $("#pNameSelect").attr("value", SysMenuInfoDlg.pNameZtree.getSelectedVal());
    $("#pcode").attr("value", treeNode.id);
};

/**
 * 显示上级菜单选择的树
 *
 * @returns
 */
SysMenuInfoDlg.showPNameSelectTree = function() {
    // Feng.showInputTree("pName","pNameContent");
    // pNameSelect为上级菜单选择框的id
    var pNameObj = $("#pNameSelect");
    var pNameOffset = $("#pNameSelect").offset();
    //pNameContent为上级菜单弹出框的id
    $("#pNameContent").css({
        left: pNameOffset.left + "px",
        top: pNameOffset.top + pNameObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onpNameBodyDown);
};

/**
 * 隐藏上级菜单选择的树
 */
SysMenuInfoDlg.hidepNameSelectTree = function () {
    //pNameContent为上级菜单弹出框的id
    $("#pNameContent").fadeOut("fast");
    $("body").unbind("mousedown", onpNameBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

function onpNameBodyDown(event) {
    //menuContent为上级菜单弹出框pNameContent的class样式
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        SysMenuInfoDlg.hidepNameSelectTree();
    }
};

/**
 * 收集数据
 */
SysMenuInfoDlg.collectData = function() {
    this.set('id').set('name').set('code').set('icon').set('url').set('sort').set('detail').set('isMenu').set('levels')
        .set('pcode').set('isOpen').set('status');
};

/**
 * 提交添加系统菜单
 */
SysMenuInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/sys-menu/save", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.SysMenu.table.refresh();
        SysMenuInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
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
    var ajax = new $ax(Feng.ctxPath + "/api/sys-menu/update", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        window.parent.SysMenu.table.refresh();
        SysMenuInfoDlg.close();
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.sysMenuInfoData);
    ajax.start();
};

$(function() {

    var pNameTree = new $ZTree("pNameTree", "/api/sys-menu/getAllMenuTree");
    pNameTree.bindOnClick(SysMenuInfoDlg.onClickPName);
    pNameTree.init();
    SysMenuInfoDlg.pNameZtree = pNameTree;

    //初始化状态选项
    $("#status").val($("#statusValue").val());
    $("#isMenu").val($("#isMenuValue").val());
    $("#isOpen").val($("#isOpenValue").val());
});

