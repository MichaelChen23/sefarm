/**
 * 订单项 管理的单例
 */
var OrderItem = {
    id: "orderItemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderItem.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单id', field: 'orderId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '订单号', field: 'orderNo', align: 'center', valign: 'middle', sortable: true},
        {title: '产品id', field: 'productId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '产品名称', field: 'productName', align: 'center', valign: 'middle', sortable: true},
        {title: '单价', field: 'price', align: 'center', valign: 'middle', sortable: true},
        {title: '数量', field: 'number', align: 'center', valign: 'middle', sortable: true},
        {title: '单位', field: 'unit', align: 'center', valign: 'middle', sortable: true},
        {title: '配送费', field: 'carryFee', align: 'center', valign: 'middle', sortable: true},
        {title: '总金额', field: 'total', align: 'center', valign: 'middle', sortable: true},
        {title: '是否评价', field: 'commentFlag', align: 'center', valign: 'middle', sortable: true, formatter: commentStatusFormatter},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {title: '用户帐号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 是否评论判断
 * @param value
 * @param row
 * @param index
 */
function commentStatusFormatter(value, row, index) {
    if (row['commentFlag'] == 'y') {
        return '是';
    }
    if (row['commentFlag'] == 'n') {
        return '否';
    }
    return value;
};

/**
 * 检查是否选中
 */
OrderItem.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        OrderItem.seItem = selected[0];
        return true;
    }
};

OrderItem.resetSearch = function () {
    $("#name").val("");
    $("#orderNo").val("");
    $("#productName").val("");
    $("#commentFlag").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    OrderItem.search();
};


OrderItem.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['orderNo'] = $("#orderNo").val();
    queryData['productName'] = $("#productName").val();
    queryData['commentFlag'] = $("#commentFlag").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    OrderItem.table.refresh({query: queryData});
};

/**
 * 点击添加 订单项
 */
OrderItem.openSaveOrderItem = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单项',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/order-item/item_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 订单项
 * @param itemId 订单项id
 */
OrderItem.openUpdateOrderItem = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑订单项',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/order-item/item_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 订单项
 */
OrderItem.delOrderItem = function () {
    if (this.check()) {

        var operation = function(){
            var itemId = OrderItem.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/order-item/remove", function () {
                Feng.success("删除成功!");
                OrderItem.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("itemId", itemId);
            ajax.start();
        };

        Feng.confirm("是否删除订单号为" + OrderItem.seItem.orderNo + "的订单项?",operation);
    }
};

$(function () {
    var defaultColunms = OrderItem.initColumn();
    var table = new BSTable("orderItemTable", "/api/order-item/item_list", defaultColunms);
    table.setPaginationType("server");
    OrderItem.table = table.init();
});