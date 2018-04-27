/**
 * 产品类型 管理的单例
 */
var ProdType = {
    id: "prodTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProdType.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '类型名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '产品目录', field: 'productCatalogName', align: 'center', valign: 'middle', sortable: true},
        {title: '图片', field: 'image', align: 'center', valign: 'middle', sortable: true},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '详述', field: 'detail', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: statusFormatter},
        {title: '创建人', field: 'createBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};


/**
 * 检查是否选中
 */
ProdType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ProdType.seItem = selected[0];
        return true;
    }
};

ProdType.resetSearch = function () {
    $("#name").val("");
    $("#catalog").attr("",'全部');
    $("#beginTime").val("");
    $("#endTime").val("");

    ProdType.search();
};


ProdType.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['productCatalogId'] = $("#catalog").find("option:selected").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    ProdType.table.refresh({query: queryData});
};

/**
 * 点击添加 产品类型
 */
ProdType.openSaveProdType = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品类型',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/prod-type/type_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 产品类型
 * @param typeId 产品类型id
 */
ProdType.openUpdateProdType = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑产品类型',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/prod-type/type_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 产品类型
 */
ProdType.delProdType = function () {
    if (this.check()) {

        var operation = function(){
            var typeId = ProdType.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/prod-type/remove", function () {
                Feng.success("删除成功!");
                ProdType.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("typeId", typeId);
            ajax.start();
        };

        Feng.confirm("是否删除 产品类型" + ProdType.seItem.name + "?",operation);
    }
};

$(function () {
    var defaultColunms = ProdType.initColumn();
    var table = new BSTable("prodTypeTable", "/api/prod-type/type_list", defaultColunms);
    table.setPaginationType("server");
    ProdType.table = table.init();

    //获取所有的产品目录
    $.ajax({
        url: '/api/prod-cata/getAll',
        async: false,
        type: "GET",
        data: {},
        success: function (data, status) {
            if (data.code == 200) {
                $.each(data.result, function (key, value) {
                    var option = $("<option>").val(value.id).text(value.name);
                    $("#catalog").append(option);
                });
                //添加全部类型到类型选择框，并选中全部
                $("#catalog").append("<option value='' selected>全部</option>");
            }
        }
    });

});