/**
 * 系统用户详情对话框（可用于添加和修改对话框）
 */
var SysUserInfoDlg = {
    sysUserInfoData: {}
};

/**
 * 清除数据
 */
SysUserInfoDlg.clearData = function () {
    this.sysUserInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysUserInfoDlg.set = function (key, val) {
    this.sysUserInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysUserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
SysUserInfoDlg.close = function () {
    parent.layer.close(window.parent.SysUser.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
SysUserInfoDlg.onClickDept = function (e, treeId, treeNode) {
    //deptSelect为部门选择框的id
    $("#deptSelect").attr("value", instance.getSelectedVal());
    //sysDeptId为上传部门id
    $("#sysDeptId").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
SysUserInfoDlg.showDeptSelectTree = function () {
    //deptSelect为部门选择框的id
    var deptObj = $("#deptSelect");
    var deptOffset = $("#deptSelect").offset();
    //deptContent为部门弹出框的id
    $("#deptContent").css({
        left: deptOffset.left + "px",
        top: deptOffset.top + deptObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示 系统用户信息 部门选择的树 下拉框
 * add by mc 2018-7-3
 * @returns
 */
SysUserInfoDlg.showSysUserInfoDeptSelectTree = function () {
    //deptSelect为部门选择框的id
    var deptObj = $("#deptSelect");
    var deptOffset = $("#deptSelect").position();
    //deptContent为部门弹出框的id
    $("#deptContent").css({
        left: deptOffset.left + "px",
        top: deptOffset.top + deptObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
SysUserInfoDlg.hideDeptSelectTree = function () {
    //deptContent为部门弹出框的id
    $("#deptContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

function onBodyDown(event) {
    //menuContent为部门弹出框deptContent的class样式
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        SysUserInfoDlg.hideDeptSelectTree();
    }
};

/**
 * 收集数据
 */
SysUserInfoDlg.collectData = function () {
    this.set('id').set('username').set('password').set('rePassword').set('avatar')
        .set('name').set('sex').set('birthday').set('phone').set('mobile').set('email')
        .set('sysDeptId').set('status').set('remark');
};

/**
 * 验证两个密码是否一致
 */
SysUserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 提交添加用户
 */
SysUserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validatePwd()) {
        Feng.error("两次密码输入不一致");
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/sys-user/save", function (data) {
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.SysUser.table.refresh();
        SysUserInfoDlg.close();
    }, function (data) {
        Feng.infoDetail("添加失败!", data.msg);
    });
    ajax.set(this.sysUserInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
SysUserInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/sys-user/update", function (data) {
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        if(window.parent.SysUser != undefined){
            window.parent.SysUser.table.refresh();
            SysUserInfoDlg.close();
        }
    }, function (data) {
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.sysUserInfoData);
    ajax.start();
};

/**
 * 修改密码
 */
SysUserInfoDlg.changePwd = function () {
    var ajax = new $ax(Feng.ctxPath + "/api/sys-user/changePwd", function (data) {
        if (data.code == 200) {
            Feng.success("修改密码成功!");
        } else {
            Feng.infoDetail("修改密码失败!", data.msg);
        }
    }, function (data) {
        Feng.infoDetail("修改密码失败!", data.msg);
    });
    ajax.set("oldPwd");
    ajax.set("newPwd");
    ajax.set("rePwd");
    ajax.start();

};

$(function () {
    var ztree = new $ZTree("treeDemo", "/api/sys-dept/getDeptTree");
    ztree.bindOnClick(SysUserInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    //初始化性别选项
    $("#sex").val($("#sexValue").val());

    //初始化状态选项
    $("#status").val($("#statusValue").val());

    // 初始化头像上传
    var pictureUp = new $WebUpload("avatar");
    pictureUp.init();
});


