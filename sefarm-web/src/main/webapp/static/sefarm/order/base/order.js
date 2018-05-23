/**
 * 订单 管理的单例
 */
var Order = {
    id: "orderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单号', field: 'orderNo', align: 'center', valign: 'middle', sortable: true},
        {title: '用户账号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '付款方式', field: 'payType', align: 'center', valign: 'middle', sortable: true},
        {title: '运送方式', field: 'carry', align: 'center', valign: 'middle', sortable: true},
        {title: '运送总费用', field: 'carryFeeTotal', align: 'center', valign: 'middle', sortable: true},
        {title: '产品总数量', field: 'quantity', align: 'center', valign: 'middle', sortable: true},
        {title: '产品总金额', field: 'productTotal', align: 'center', valign: 'middle', sortable: true},
        {title: '订单总金额', field: 'amount', align: 'center', valign: 'middle', sortable: true},
        {title: '兑换总积分', field: 'exchangeScore', align: 'center', valign: 'middle', sortable: true},
        {title: '客户要求', field: 'requirement', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {title: '订单状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: orderStatusFormatter},
        {title: '下单时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter}
    ];
    return columns;
};

/**
 * 订单状态判断
 * @param value
 * @param row
 * @param index
 */
function orderStatusFormatter(value, row, index) {
    if (row['status'] == 'y') {
        return '完成';
    }
    if (row['status'] == 'n') {
        return '未完成';
    }
    return value;
};

/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Order.seItem = selected[0];
        return true;
    }
};

Order.resetSearch = function () {
    $("#name").val("");
    $("#orderNo").val("");
    $("#status").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Order.search();
};


Order.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['orderNo'] = $("#orderNo").val();
    queryData['status'] = $("#status").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    Order.table.refresh({query: queryData});
};

/**
 * 点击添加 订单
 */
Order.openSaveOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/order/order_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 订单
 * @param orderId 订单id
 */
Order.openUpdateOrder = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑订单',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/order/order_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 订单
 */
Order.delOrder = function () {
    if (this.check()) {

        var operation = function(){
            var orderId = Order.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/order/removeOrder", function () {
                Feng.success("删除成功!");
                Order.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("orderId", orderId);
            ajax.start();
        };

        Feng.confirm("是否删除订单号为" + Order.seItem.orderNo + "的订单?",operation);
    }
};

$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable("orderTable", "/api/order/list", defaultColunms);
    table.setPaginationType("server");
    Order.table = table.init();
});