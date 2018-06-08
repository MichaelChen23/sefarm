/**
 * 购物车 管理的单例
 */
var Cart = {
    id: "cartTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Cart.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户帐号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '产品id', field: 'productId', align: 'center', valign: 'middle', sortable: true},
        {title: '产品名', field: 'productName', align: 'center', valign: 'middle', sortable: true},
        {title: '产品图片', field: 'productImage', align: 'center', valign: 'middle', sortable: true, formatter: imageFormatter},
        {title: '产品现价', field: 'nowPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '产品数量', field: 'number', align: 'center', valign: 'middle', sortable: true},
        {title: '产品单位', field: 'unit', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 获取产品图片展示判断
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function imageFormatter(value, row, index){
    if (value != null && value != "") {
        return "<img  src='/images/"+ value + "' width='88' height='88'>";
    }
};

/**
 * 检查是否选中
 */
Cart.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Cart.seItem = selected[0];
        return true;
    }
};

Cart.resetSearch = function () {
    $("#account").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Cart.search();
};


Cart.search = function () {
    var queryData = {};

    queryData['account'] = $("#account").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    Cart.table.refresh({query: queryData});
};

/**
 * 点击添加 购物车产品
 */
Cart.openSaveCart = function () {
    var index = layer.open({
        type: 2,
        title: '添加购物车产品',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/cart/cart_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 购物车产品
 * @param cartId 购物车产品id
 */
Cart.openUpdateCart = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑购物车产品',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/cart/cart_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 购物车产品
 */
Cart.delCart = function () {
    if (this.check()) {

        var operation = function(){
            var cartId = Cart.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/cart/removeCart", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                } else {
                    Feng.infoDetail("删除失败!", data.msg);
                }
                Cart.table.refresh();
            }, function (data) {
                Feng.infoDetail("删除失败!", data.msg);
            });
            ajax.set("cartId", cartId);
            ajax.start();
        };

        Feng.confirm("是否删除购物车产品" + Cart.seItem.productName + "?",operation);
    }
};

$(function () {
    var defaultColunms = Cart.initColumn();
    var table = new BSTable("cartTable", "/api/cart/cart_list", defaultColunms);
    table.setPaginationType("server");
    Cart.table = table.init();
});