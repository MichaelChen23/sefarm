/**
 * 订单支付记录 管理的单例
 */
var OrderPay = {
    id: "orderPayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderPay.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单id', field: 'orderId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '订单号', field: 'orderNo', align: 'center', valign: 'middle', sortable: true},
        {title: '用户帐号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '支付金额', field: 'payAmount', align: 'center', valign: 'middle', sortable: true},
        {title: '支付时间', field: 'payTime', align: 'center', valign: 'middle', sortable: true},
        {title: '支付方式', field: 'payType', align: 'center', valign: 'middle', sortable: true},
        {title: '实际支付账号', field: 'payAccount', align: 'center', valign: 'middle', sortable: true},
        {title: '支付流水号', field: 'payTradeNo', align: 'center', valign: 'middle', sortable: true},
        {title: '支付状态', field: 'payStatus', align: 'center', valign: 'middle', sortable: true},
        {title: '状态更新时间', field: 'updateTime', align: 'center', valign: 'middle', sortable: true},
        {title: '支付结束时间', field: 'endTime', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
OrderPay.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        OrderPay.seItem = selected[0];
        return true;
    }
};

OrderPay.resetSearch = function () {
    $("#name").val("");
    $("#payAccount").val("");
    $("#orderNo").val("");
    $("#payStatus").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    OrderPay.search();
};


OrderPay.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['payAccount'] = $("#payAccount").val();
    queryData['orderNo'] = $("#orderNo").val();
    queryData['payStatus'] = $("#payStatus").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    OrderPay.table.refresh({query: queryData});
};

/**
 * 点击添加 订单支付记录
 */
OrderPay.openSaveOrderPay = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单支付记录',
        area: ['800px', '680px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/order-pay/pay_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 订单支付记录
 * @param payId 订单支付记录id
 */
OrderPay.openUpdateOrderPay = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑订单支付记录',
            area: ['800px', '680px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/order-pay/pay_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 订单支付记录
 */
OrderPay.delOrderPay = function () {
    if (this.check()) {

        var operation = function(){
            var payId = OrderPay.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/order-pay/remove", function () {
                Feng.success("删除成功!");
                OrderPay.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("payId", payId);
            ajax.start();
        };

        Feng.confirm("是否删除 用户" + OrderPay.seItem.account + " 的订单支付记录?", operation);
    }
};

$(function () {
    var defaultColunms = OrderPay.initColumn();
    var table = new BSTable("orderPayTable", "/api/order-pay/pay_list", defaultColunms);
    table.setPaginationType("server");
    OrderPay.table = table.init();

});