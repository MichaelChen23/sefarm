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
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#sysDeptId").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
SysUserInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
SysUserInfoDlg.showInfoDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityPosition = $("#citySel").position();
    $("#menuContent").css({
        left: cityPosition.left + "px",
        top: cityPosition.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
SysUserInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
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
    var ajax = new $ax(Feng.ctxPath + "/sys-user/save", function (data) {
        Feng.success("添加成功!");
        window.parent.SysUser.table.refresh();
        SysUserInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
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
    var ajax = new $ax(Feng.ctxPath + "/sys-user/update", function (data) {
        Feng.success("修改成功!");
        if(window.parent.SysUser != undefined){
            window.parent.SysUser.table.refresh();
            SysUserInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysUserInfoData);
    ajax.start();
};

/**
 * 修改密码
 */
SysUserInfoDlg.chPwd = function () {
    var ajax = new $ax(Feng.ctxPath + "/sys-user/changePwd", function (data) {
        Feng.success("修改成功!");
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set("oldPwd");
    ajax.set("newPwd");
    ajax.set("rePwd");
    ajax.start();

};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        SysUserInfoDlg.hideDeptSelectTree();
    }
}

$(function () {
    var ztree = new $ZTree("treeDemo", "/sys-dept/getDeptTree");
    ztree.bindOnClick(SysUserInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    //初始化性别选项
    $("#sex").val($("#sexValue").val());

    //初始化状态选项
    $("#status").val($("#statusValue").val());

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.init();
});


