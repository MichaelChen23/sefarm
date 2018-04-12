/**
 * 角色管理的单例
 */
var SysRole = {
    id: "sysRoleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SysRole.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '角色名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '角色代码', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '上级角色', field: 'pName', align: 'center', valign: 'middle', sortable: true},
        {title: '所在部门', field: 'deptName', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: statusFormatter},
        {title: '创建人', field: 'createBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 状态判断
 * @param value
 * @param row
 * @param index
 */
function statusFormatter(value, row, index) {
    if (row['status'] == 'y') {
        return '开启';
    }
    if (row['status'] == 'n') {
        return '停用';
    }
    return value;
};

/**
 * 检查是否选中
 */
SysRole.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        SysRole.seItem = selected[0];
        return true;
    }
};

SysRole.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    SysRole.search();
};


SysRole.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    SysRole.table.refresh({query: queryData});
};

/**
 * 点击添加系统角色
 */
SysRole.openSaveSysRole = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统角色',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys-role/sysrole_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 系统角色id
 */
SysRole.openUpdateSysRole = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑系统角色',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys-role/sysrole_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统角色
 */
SysRole.delSysRole = function () {
    if (this.check()) {

        var operation = function(){
            var roleId = SysRole.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/sys-role/remove", function () {
                Feng.success("删除成功!");
                SysRole.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("roleId", roleId);
            ajax.start();
        };

        Feng.confirm("是否删除系统角色" + SysRole.seItem.name + "?",operation);
    }
};

/**
 * 系统角色的权限配置
 */
SysRole.assignAuth = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '权限配置',
            area: ['300px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys-role/sysrole_assignauth/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

$(function () {
    var defaultColunms = SysRole.initColumn();
    var table = new BSTable("sysRoleTable", "/sys-role/sysrole_list", defaultColunms);
    table.setPaginationType("server");
    SysRole.table = table.init();
});

