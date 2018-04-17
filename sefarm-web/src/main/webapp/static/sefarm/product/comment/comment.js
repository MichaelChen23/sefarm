/**
 * 产品评论 管理的单例
 */
var ProductComment = {
    id: "productCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProductComment.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '产品id', field: 'productId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '产品名', field: 'productName', align: 'center', valign: 'middle', sortable: true},
        {title: '用户帐号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '用户名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '订单id', field: 'orderId', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '订单号', field: 'orderNo', align: 'center', valign: 'middle', sortable: true},
        {title: '评论内容', field: 'content', align: 'center', valign: 'middle', sortable: true},
        {title: '星级', field: 'star', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: statusFormatter},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '回复人', field: 'replier', align: 'center', valign: 'middle', sortable: true},
        {title: '回复评论', field: 'reply', align: 'center', valign: 'middle', sortable: true},
        {title: '回复时间', field: 'replyTime', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
ProductComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ProductComment.seItem = selected[0];
        return true;
    }
};

ProductComment.resetSearch = function () {
    $("#name").val("");
    $("#productName").val("");
    $("#orderNo").val("");
    $("#stars").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    ProductComment.search();
};


ProductComment.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['productName'] = $("#productName").val();
    queryData['orderNo'] = $("#orderNo").val();
    queryData['stars'] = $("#stars").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    ProductComment.table.refresh({query: queryData});
};

/**
 * 点击添加 产品评论
 */
ProductComment.openSaveProductComment = function () {
    var index = layer.open({
        type: 2,
        title: '添加产品评论',
        area: ['800px', '680px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/prod-comment/comment_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 产品评论
 * @param commentId 产品评论id
 */
ProductComment.openUpdateProductComment = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑产品评论',
            area: ['800px', '680px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/prod-comment/comment_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 产品评论
 */
ProductComment.delProductComment = function () {
    if (this.check()) {

        var operation = function(){
            var commentId = ProductComment.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/prod-comment/remove", function () {
                Feng.success("删除成功!");
                ProductComment.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("commentId", commentId);
            ajax.start();
        };

        Feng.confirm("是否删除 用户" + ProductComment.seItem.name + " 的产品评论?",operation);
    }
};

$(function () {
    var defaultColunms = ProductComment.initColumn();
    var table = new BSTable("productCommentTable", "/prod-comment/list", defaultColunms);
    table.setPaginationType("server");
    ProductComment.table = table.init();

});