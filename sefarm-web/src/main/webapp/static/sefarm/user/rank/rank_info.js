/**
 * 用户等级 详情对话框（可用于添加和修改对话框）
 */
var RankInfoDlg = {
    rankInfoData: {}
};

/**
 * 清除数据
 */
RankInfoDlg.clearData = function () {
    this.rankInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RankInfoDlg.set = function (key, val) {
    this.rankInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RankInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RankInfoDlg.close = function () {
    parent.layer.close(window.parent.Rank.layerIndex);
};

/**
 * 收集数据
 */
RankInfoDlg.collectData = function() {
    this.set('id').set('code').set('name').set('minScore').set('maxScore').set('remark').set('createBy').set('createTime').set('updateBy').set('updateTime');
};

/**
 * 提交添加 用户等级
 */
RankInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/user-rank/saveRank", function(data){
        if (data.code == 200) {
            Feng.success("添加成功!");
        } else {
            Feng.infoDetail("添加失败!", data.msg);
        }
        window.parent.Rank.table.refresh();
        RankInfoDlg.close();
    },function(data){
        Feng.infoDetail("添加失败!", data.msg);
    });
    ajax.set(this.rankInfoData);
    ajax.start();
};

/**
 * 提交修改 用户等级
 */
RankInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/user-rank/updateRank", function(data){
        if (data.code == 200) {
            Feng.success("修改成功!");
        } else {
            Feng.infoDetail("修改失败!", data.msg);
        }
        if(window.parent.Rank != undefined) {
            window.parent.Rank.table.refresh();
            RankInfoDlg.close();
        }
    },function(data){
        Feng.infoDetail("修改失败!", data.msg);
    });
    ajax.set(this.rankInfoData);
    ajax.start();
};

$(function() {

    //初始化时间并转换格式
    $("#createTime").val(cstTimeFormatter($("#createTimeValue").val()));
    $("#updateTime").val(cstTimeFormatter($("#updateTimeValue").val()));
});

