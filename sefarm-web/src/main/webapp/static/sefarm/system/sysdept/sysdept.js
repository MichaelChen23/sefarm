/**
 * 部门管理的单例
 */
var SysDept = {
    id: "sysDeptTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SysDept.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '部门名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '部门全称', field: 'fullName', align: 'center', valign: 'middle', sortable: true},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '详述', field: 'detail', align: 'center', valign: 'middle', sortable: true},
        {title: '上级部门', field: 'pName', align: 'center', valign: 'middle', sortable: true},
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
SysDept.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        SysDept.seItem = selected[0];
        return true;
    }
};

SysDept.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    SysDept.search();
};


SysDept.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    SysDept.table.refresh({query: queryData});
};

/**
 * 点击添加系统部门
 */
SysDept.openSaveSysDept = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统部门',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/sys-dept/sysdept_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改系统部门
 * @param deptId 系统部门id
 */
SysDept.openUpdateSysDept = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑系统部门',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/sys-dept/sysdept_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统部门
 */
SysDept.delSysDept = function () {
    if (this.check()) {

        var operation = function(){
            var deptId = SysDept.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/sys-dept/remove", function () {
                Feng.success("删除成功!");
                SysDept.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("deptId", deptId);
            ajax.start();
        };

        Feng.confirm("是否删除系统部门" + SysDept.seItem.name + "?",operation);
    }
};

$(function () {
    var defaultColunms = SysDept.initColumn();
    var table = new BSTable("sysDeptTable", "/api/sys-dept/sysdept_list", defaultColunms);
    table.setPaginationType("server");
    SysDept.table = table.init();
});