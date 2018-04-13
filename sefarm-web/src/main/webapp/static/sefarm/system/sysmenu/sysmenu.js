/**
 * 菜单管理的单例
 */
var SysMenu = {
    id: "sysMenuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SysMenu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', width:'50px'},
        {title: '菜单名', field: 'name', align: 'center', valign: 'middle', sortable: true, width:'15%'},
        {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true, width:'12%'},
        {title: '图标', field: 'icon', align: 'center', valign: 'middle', sortable: true},
        {title: '链接', field: 'url', align: 'center', valign: 'middle', sortable: true, width:'18%'},
        {title: '排序号', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '是否菜单', field: 'isMenu', align: 'center', valign: 'middle', sortable: true},
        {title: '菜单层级', field: 'levels', align: 'center', valign: 'middle', sortable: true},
        {title: '上级菜单编号', field: 'pcode', align: 'center', valign: 'middle', sortable: true},
        {title: '是否打开', field: 'isOpen', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true},
        {title: '描述', field: 'description', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 检查是否选中
 */
SysMenu.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        SysMenu.seItem = selected[0];
        return true;
    }
};

SysMenu.resetSearch = function () {
    $("#name").val("");
    $("#level").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    SysDept.search();
};


SysMenu.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['level'] = $("#level").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    SysMenu.table.refresh({query: queryData});
};

/**
 * 点击添加系统菜单
 */
SysMenu.openSaveSysMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统菜单',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sys-menu/sysmenu_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改系统菜单
 * @param menuId 系统菜单id
 */
SysMenu.openUpdateSysMenu = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑系统菜单',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sys-menu/sysmenu_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统部门
 */
SysMenu.delSysMenu = function () {
    if (this.check()) {

        var operation = function(){
            var menuId = SysMenu.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/sys-menu/remove", function () {
                Feng.success("删除成功!");
                SysMenu.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("menuId", menuId);
            ajax.start();
        };

        Feng.confirm("是否删除系统菜单?", operation);
    }
};

$(function () {
    var defaultColunms = SysMenu.initColumn();
    var table = new BSTreeTable(SysMenu.id, "/sys-menu/all_list", defaultColunms);
    //设置层级展开
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("pcode");
    table.setExpandAll(true);
    table.init();
    SysMenu.table = table;
});