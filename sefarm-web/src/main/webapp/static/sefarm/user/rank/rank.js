/**
 * 用户等级 管理的单例
 */
var Rank = {
    id: "rankTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Rank.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '等级编码', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '等级名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '最小积分', field: 'minScore', align: 'center', valign: 'middle', sortable: true},
        {title: '最大积分', field: 'maxScore', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
Rank.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Rank.seItem = selected[0];
        return true;
    }
};

Rank.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Rank.search();
};


Rank.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    Rank.table.refresh({query: queryData});
};

/**
 * 点击添加 用户等级
 */
Rank.openSaveRank = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户等级',
        area: ['800px', '680px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/user-rank/rank_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 用户等级
 * @param rankId 用户等级id
 */
Rank.openUpdateRank = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑用户等级',
            area: ['800px', '680px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/user-rank/rank_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 用户等级
 */
Rank.delRank = function () {
    if (this.check()) {

        var operation = function(){
            var rankId = Rank.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/user-rank/removeRank", function () {
                Feng.success("删除成功!");
                Rank.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("rankId", rankId);
            ajax.start();
        };

        Feng.confirm("是否删除该名为：" + Rank.seItem.name + "的用户等级?", operation);
    }
};

$(function () {
    var defaultColunms = Rank.initColumn();
    var table = new BSTable("rankTable", "/api/user-rank/rank_list", defaultColunms);
    table.setPaginationType("server");
    Rank.table = table.init();
});