/**
 * 产品 管理的单例
 */
var Product = {
    id: "productTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Product.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '产品名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '产品简介', field: 'introduce', align: 'center', valign: 'middle', sortable: true},
        {title: '定价', field: 'price', align: 'center', valign: 'middle', sortable: true},
        {title: '现价', field: 'nowPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '小图片', field: 'picture', align: 'center', valign: 'middle', sortable: true},
        {title: '是否新品', field: 'newFlag', align: 'center', valign: 'middle', sortable: true, formatter: newFlagFormatter},
        {title: '是否特价', field: 'saleFlag', align: 'center', valign: 'middle', sortable: true, formatter: saleFlagFormatter},
        {title: '产品单位', field: 'unit', align: 'center', valign: 'middle', sortable: true},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '销量', field: 'sellCount', align: 'center', valign: 'middle', sortable: true},
        {title: '库存', field: 'stock', align: 'center', valign: 'middle', sortable: true},
        {title: '评价量', field: 'replyHit', align: 'center', valign: 'middle', sortable: true},
        {title: '好评量', field: 'goodHit', align: 'center', valign: 'middle', sortable: true},
        {title: '搜索关键词', field: 'searchWord', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {title: '产品类型', field: 'productTypeName', align: 'center', valign: 'middle', sortable: true},
        {title: '详情标题', field: 'title', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '详情Html', field: 'productHtml', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '详情images', field: 'images', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '详情描述', field: 'detail', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: prodStatusFormatter},
        {title: '创建人', field: 'createBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 是否新品判断
 * @param value
 * @param row
 * @param index
 */
function newFlagFormatter(value, row, index) {
    if (row['newFlag'] == 'y') {
        return '是';
    }
    if (row['newFlag'] == 'n') {
        return '否';
    }
    return value;
};

/**
 * 是否特价判断
 * @param value
 * @param row
 * @param index
 */
function saleFlagFormatter(value, row, index) {
    if (row['saleFlag'] == 'y') {
        return '是';
    }
    if (row['saleFlag'] == 'n') {
        return '否';
    }
    return value;
};

/**
 * 产品状态判断
 * @param value
 * @param row
 * @param index
 */
function prodStatusFormatter(value, row, index) {
    if (row['status'] == 'new') {
        return '新增';
    }
    if (row['status'] == 'on') {
        return '上架';
    }
    if (row['status'] == 'off') {
        return '下架';
    }
    if (row['status'] == 'del') {
        return '已删除';
    }
    return value;
};

/**
 * 检查是否选中
 */
Product.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Product.seItem = selected[0];
        return true;
    }
};

Product.resetSearch = function () {
    $("#name").val("");
    $("#type").attr("",'全部');
    $("#beginTime").val("");
    $("#endTime").val("");

    Product.search();
};


Product.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['productTypeId'] = $("#type").find("option:selected").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    Product.table.refresh({query: queryData});
};

/**
 * 点击添加 产品
 */
Product.openSaveProduct = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/prod/product_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 产品
 * @param prodId 产品id
 */
Product.openUpdateProduct = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑产品',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/prod/product_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 产品
 */
Product.delProduct = function () {
    if (this.check()) {

        var operation = function(){
            var prodId = Product.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/prod/remove", function () {
                Feng.success("删除成功!");
                Product.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("prodId", prodId);
            ajax.start();
        };

        Feng.confirm("是否删除 产品" + Product.seItem.name + "?",operation);
    }
};

$(function () {
    var defaultColunms = Product.initColumn();
    var table = new BSTable("productTable", "/api/prod/list", defaultColunms);
    table.setPaginationType("server");
    Product.table = table.init();

    //获取所有的产品类型
    $.ajax({
        url: '/api/prod-type/getAll',
        async: false,
        type: "GET",
        data: {},
        success: function (data, status) {
            if (data.code == 200) {
                $.each(data.result, function (key, value) {
                    var option = $("<option>").val(value.id).text(value.name);
                    $("#type").append(option);
                });
                //添加全部类型到类型选择框，并选中全部
                $("#type").append("<option value='' selected>全部</option>");
            }
        }
    });

});