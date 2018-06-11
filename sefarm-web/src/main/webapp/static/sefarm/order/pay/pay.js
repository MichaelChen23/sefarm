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
        {title: '支付时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '支付方式', field: 'payType', align: 'center', valign: 'middle', sortable: true},
        {title: '唯一标识', field: 'openid', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '公众号id', field: 'appId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '秒级时间戳', field: 'timeStamp', align: 'center', valign: 'middle', sortable: true},
        {title: '随机字符串', field: 'nonceStr', align: 'center', valign: 'middle', sortable: true},
        {title: '扩展字符串', field: 'prepayId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '签名类型', field: 'signType', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '签名', field: 'paySign', align: 'center', valign: 'middle', sortable: true},
        {title: '商户号', field: 'mchId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '设备号', field: 'deviceInfo', align: 'center', valign: 'middle', sortable: true},
        {title: '交易类型', field: 'tradeType', align: 'center', valign: 'middle', sortable: true},
        {title: '支付状态', field: 'payStatus', align: 'center', valign: 'middle', sortable: true},
        {title: '错误码', field: 'errCode', align: 'center', valign: 'middle', sortable: true},
        {title: '错误码描述', field: 'errCodeDes', align: 'center', valign: 'middle', sortable: true},
        {title: '支付ip地址', field: 'payIp', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '状态更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '支付结束时间', field: 'endTime', align: 'center', valign: 'middle', sortable: true},
        {title: '退款时间', field: 'refundTime', visible: false, align: 'center', valign: 'middle', sortable: true}
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
    $("#orderNo").val("");
    $("#payStatus").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    OrderPay.search();
};


OrderPay.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
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
        area: ['800px', '760px'], //宽高
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
            area: ['800px', '760px'], //宽高
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
            var ajax = new $ax(Feng.ctxPath + "/api/order-pay/remove", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                } else {
                    Feng.infoDetail("删除失败!", data.msg);
                }
                OrderPay.table.refresh();
            }, function (data) {
                Feng.infoDetail("删除失败!", data.msg);
            });
            ajax.set("payId", payId);
            ajax.start();
        };

        Feng.confirm("是否删除 订单号为：" + OrderPay.seItem.orderNo + " 的订单支付记录?", operation);
    }
};

$(function () {
    var defaultColunms = OrderPay.initColumn();
    var table = new BSTable("orderPayTable", "/api/order-pay/pay_list", defaultColunms);
    table.setPaginationType("server");
    OrderPay.table = table.init();

});