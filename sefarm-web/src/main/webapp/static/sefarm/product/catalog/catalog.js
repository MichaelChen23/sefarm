/**
 * 产品目录 管理的单例
 */
var ProdCatalog = {
    id: "prodCatalogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProdCatalog.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '目录名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '详述', field: 'detail', align: 'center', valign: 'middle', sortable: true},
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
ProdCatalog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ProdCatalog.seItem = selected[0];
        return true;
    }
};

ProdCatalog.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    ProdCatalog.search();
};


ProdCatalog.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    ProdCatalog.table.refresh({query: queryData});
};

/**
 * 点击添加 产品目录
 */
ProdCatalog.openSaveProdCatalog = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品目录',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/prod-cata/catalog_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 产品目录
 * @param catalogId 产品目录id
 */
ProdCatalog.openUpdateProdCatalog = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑产品目录',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/prod-cata/catalog_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 产品目录
 */
ProdCatalog.delProdCatalog = function () {
    if (this.check()) {

        var operation = function(){
            var catalogId = ProdCatalog.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/prod-cata/remove", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                } else {
                    Feng.infoDetail("删除失败!", data.msg);
                }
                ProdCatalog.table.refresh();
            }, function (data) {
                Feng.infoDetail("删除失败!", data.msg);
            });
            ajax.set("catalogId", catalogId);
            ajax.start();
        };

        Feng.confirm("是否删除 产品目录" + ProdCatalog.seItem.name + "?",operation);
    }
};

$(function () {
    var defaultColunms = ProdCatalog.initColumn();
    var table = new BSTable("prodCatalogTable", "/api/prod-cata/catalog_list", defaultColunms);
    table.setPaginationType("server");
    ProdCatalog.table = table.init();
});