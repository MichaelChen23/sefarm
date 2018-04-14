/**
 * 广告 管理的单例
 */
var Advert = {
    id: "advertTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Advert.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
        {title: '图片', field: 'image', align: 'center', valign: 'middle', sortable: true},
        {title: '内容', field: 'content', align: 'center', valign: 'middle', sortable: true},
        {title: '链接', field: 'url', align: 'center', valign: 'middle', sortable: true},
        {title: '上架时间', field: 'startTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '下架时间', field: 'endTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '类别', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {title: 'html内容', field: 'html', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: statusFormatter},
        {title: '创建人', field: 'createBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter}
    ];
    return columns;
};


/**
 * 检查是否选中
 */
Advert.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Advert.seItem = selected[0];
        return true;
    }
};

Advert.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Advert.search();
};


Advert.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    Advert.table.refresh({query: queryData});
};

/**
 * 点击添加 广告
 */
Advert.openSaveAdvert = function () {
    var index = layer.open({
        type: 2,
        title: '添加广告',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/advert/advert_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 广告
 * @param advertId 广告id
 */
Advert.openUpdateAdvert = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑广告',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/advert/advert_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除广告
 */
Advert.delAdvert = function () {
    if (this.check()) {

        var operation = function(){
            var advertId = Advert.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/advert/remove", function () {
                Feng.success("删除成功!");
                Advert.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("advertId", advertId);
            ajax.start();
        };

        Feng.confirm("是否删除广告" + Advert.seItem.title + "?",operation);
    }
};

$(function () {
    var defaultColunms = Advert.initColumn();
    var table = new BSTable("advertTable", "/advert/advert_list", defaultColunms);
    table.setPaginationType("server");
    Advert.table = table.init();
});