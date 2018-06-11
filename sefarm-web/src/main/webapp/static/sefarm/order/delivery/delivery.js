/**
 * 订单配送 管理的单例
 */
var OrderDelivery = {
    id: "orderDeliveryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderDelivery.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单id', field: 'orderId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '订单号', field: 'orderNo', align: 'center', valign: 'middle', sortable: true},
        {title: '系统部门id', field: 'sysDeptId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '系统部门名', field: 'sysDeptName', align: 'center', valign: 'middle', sortable: true},
        {title: '配送人id', field: 'sysUserId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '配送人帐号', field: 'sysUserName', align: 'center', valign: 'middle', sortable: true},
        {title: '收货人', field: 'receiver', align: 'center', valign: 'middle', sortable: true},
        {title: '收货地址', field: 'address', align: 'center', valign: 'middle', sortable: true},
        {title: '收货人电话', field: 'mobile', align: 'center', valign: 'middle', sortable: true},
        {title: '快递', field: 'expressName', align: 'center', valign: 'middle', sortable: true},
        {title: '快递单号', field: 'expressNo', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: deliveryStatusFormatter},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '状态更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '发货时间', field: 'deliveryTime', align: 'center', valign: 'middle', sortable: true},
        {title: '收货时间', field: 'receiveTime', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 配送状态判断
 * @param value
 * @param row
 * @param index
 */
function deliveryStatusFormatter(value, row, index) {
    if (row['status'] == 'new') {
        return '新增';
    }
    if (row['status'] == 'check') {
        return '审核中';
    }
    if (row['status'] == 'ready') {
        return '待发货';
    }
    if (row['status'] == 'delivery') {
        return '已发货';
    }
    if (row['status'] == 'receive') {
        return '已接收';
    }
    if (row['status'] == 'cancel') {
        return '已取消';
    }
    return value;
};

/**
 * 检查是否选中
 */
OrderDelivery.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        OrderDelivery.seItem = selected[0];
        return true;
    }
};

OrderDelivery.resetSearch = function () {
    $("#orderNo").val("");
    $("#deptName").val("");
    $("#name").val("");
    $("#status").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    OrderDelivery.search();
};


OrderDelivery.search = function () {
    var queryData = {};

    queryData['orderNo'] = $("#orderNo").val();
    queryData['deptName'] = $("#deptName").val();
    queryData['name'] = $("#name").val();
    queryData['status'] = $("#status").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    OrderDelivery.table.refresh({query: queryData});
};

/**
 * 点击添加 订单配送
 */
OrderDelivery.openSaveOrderDelivery = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单配送',
        area: ['800px', '680px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/order-dely/delivery_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 订单配送
 * @param deliveryId 订单配送id
 */
OrderDelivery.openUpdateOrderDelivery = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑订单配送',
            area: ['800px', '680px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/order-dely/delivery_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 订单配送
 */
OrderDelivery.delOrderDelivery = function () {
    if (this.check()) {

        var operation = function(){
            var deliveryId = OrderDelivery.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/order-dely/remove", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                } else {
                    Feng.infoDetail("删除失败!", data.msg);
                }
                OrderDelivery.table.refresh();
            }, function (data) {
                Feng.infoDetail("删除失败!", data.msg);
            });
            ajax.set("deliveryId", deliveryId);
            ajax.start();
        };

        Feng.confirm("是否删除 订单号为" + OrderDelivery.seItem.orderNo + " 的订单配送?", operation);
    }
};

/**
 * 订单配送——待发货
 */
OrderDelivery.readyOrder = function () {
    if (this.check()) {
        var deliveryId = OrderDelivery.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/api/order-dely/ready", function (data) {
            if (data.code == 200) {
                Feng.success("待发货成功!");
            } else {
                Feng.infoDetail("待发货失败!", data.msg);
            }
            OrderDelivery.table.refresh();
        }, function (data) {
            Feng.infoDetail("待发货失败!", data.msg);
        });
        ajax.set("deliveryId", deliveryId);
        ajax.start();
    }
};

/**
 * 订单配送——发送
 */
OrderDelivery.deliveryOrder = function () {
    if (this.check()) {
        var deliveryId = OrderDelivery.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/api/order-dely/delivery", function (data) {
            if (data.code == 200) {
                Feng.success("发货成功!");
            } else {
                Feng.infoDetail("发货失败!", data.msg);
            }
            OrderDelivery.table.refresh();
        }, function (data) {
            Feng.infoDetail("发货失败!", data.msg);
        });
        ajax.set("deliveryId", deliveryId);
        ajax.start();
    }
};

/**
 * 订单配送——已接收
 */
OrderDelivery.receiveOrder = function () {
    if (this.check()) {
        var deliveryId = OrderDelivery.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/api/order-dely/receive", function (data) {
            if (data.code == 200) {
                Feng.success("接收成功!");
            } else {
                Feng.infoDetail("接收失败!", data.msg);
            }
            OrderDelivery.table.refresh();
        }, function (data) {
            Feng.infoDetail("接收失败!", data.msg);
        });
        ajax.set("deliveryId", deliveryId);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = OrderDelivery.initColumn();
    var table = new BSTable("orderDeliveryTable", "/api/order-dely/delivery_list", defaultColunms);
    table.setPaginationType("server");
    OrderDelivery.table = table.init();

});