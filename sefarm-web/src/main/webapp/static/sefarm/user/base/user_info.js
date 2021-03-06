/**
 * 用户 详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    userInfoData: {}
};

/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, val) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.User.layerIndex);
};

/**
 * 收集数据
 */
UserInfoDlg.collectData = function() {
    this.set('id').set('name').set('mobile').set('nickname').set('openid').set('sex').set('headimgurl').set('country').set('province').set('city').set('language')
        .set('accessToken').set('accountType').set('rank').set('amount').set('score').set('status').set('createTime').set('updateBy').set('updateTime');
};

/**
 * 提交添加 用户
 */
UserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/user/saveUser", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.User.table.refresh();
        UserInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 提交修改 用户
 */
UserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/user/updateUser", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        if(window.parent.User != undefined) {
            window.parent.User.table.refresh();
            UserInfoDlg.close();
        }
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

$(function() {

    //初始化性别
    $("#sex").val($("#sexValue").val());
    //初始化帐号类别
    $("#accountType").val($("#accountTypeValue").val());
    //初始化状态
    $("#status").val($("#statusValue").val());
    //初始化时间并转换格式
    $("#createTime").val(cstTimeFormatter($("#createTimeValue").val()));
    $("#updateTime").val(cstTimeFormatter($("#updateTimeValue").val()));
    $("#lastLoginTime").val(cstTimeFormatter($("#lastLoginTimeValue").val()));
});

