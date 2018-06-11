/**
 * 用户地址 管理的单例
 */
var Address = {
    id: "addressTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Address.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户帐号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '收货人', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '省份id', field: 'provinceId', align: 'center', valign: 'middle', sortable: true},
        {title: '省份', field: 'province', align: 'center', valign: 'middle', sortable: true},
        {title: '城市id', field: 'cityId', align: 'center', valign: 'middle', sortable: true},
        {title: '城市', field: 'city', align: 'center', valign: 'middle', sortable: true},
        {title: '区域id', field: 'areaId', align: 'center', valign: 'middle', sortable: true},
        {title: '区域', field: 'area', align: 'center', valign: 'middle', sortable: true},
        {title: '详细地址', field: 'address', align: 'center', valign: 'middle', sortable: true},
        {title: '邮编', field: 'zip', align: 'center', valign: 'middle', sortable: true},
        {title: '电话/座机号码', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '手机号码', field: 'mobile', align: 'center', valign: 'middle', sortable: true},
        {title: '是否默认地址', field: 'defaultFlag', align: 'center', valign: 'middle', sortable: true, formatter: defaultFlagFormatter},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter}
    ];
    return columns;
};

/**
 * 是否默认地址判断
 * @param value
 * @param row
 * @param index
 */
function defaultFlagFormatter(value, row, index) {
    if (row['defaultFlag'] == 'y') {
        return '是';
    }
    if (row['defaultFlag'] == 'n') {
        return '否';
    }
    return value;
};

/**
 * 检查是否选中
 */
Address.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Address.seItem = selected[0];
        return true;
    }
};

Address.resetSearch = function () {
    $("#account").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Address.search();
};


Address.search = function () {
    var queryData = {};

    queryData['account'] = $("#account").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    Address.table.refresh({query: queryData});
};

/**
 * 点击添加 用户地址
 */
Address.openSaveAddress = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户地址',
        area: ['800px', '680px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/user-adr/address_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 用户地址
 * @param addressId 用户地址id
 */
Address.openUpdateAddress = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑用户地址',
            area: ['800px', '680px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/user-adr/address_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 用户地址
 */
Address.delAddress = function () {
    if (this.check()) {

        var operation = function(){
            var addressId = Address.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/user-adr/removeAddress", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                } else {
                    Feng.infoDetail("删除失败!", data.msg);
                }
                Address.table.refresh();
            }, function (data) {
                Feng.infoDetail("删除失败!", data.msg);
            });
            ajax.set("addressId", addressId);
            ajax.start();
        };

        Feng.confirm("是否删除该用户帐号为：" + Address.seItem.account + "的收货人为：" + Address.seItem.name + "地址?", operation);
    }
};

$(function () {
    var defaultColunms = Address.initColumn();
    var table = new BSTable("addressTable", "/api/user-adr/address_list", defaultColunms);
    table.setPaginationType("server");
    Address.table = table.init();
});