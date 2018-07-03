/**
 * 系统用户管理的单例对象
 */
var SysUser = {
    id: "sysUserTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
SysUser.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '账号', field: 'username', align: 'center', valign: 'middle', sortable: true},
        {title: '密码', field: 'password', align: 'center', valign: 'middle'},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '头像', field: 'avatar', align: 'center', valign: 'middle', sortable: true, formatter: imageFormatter},
        {title: '性别', field: 'sex', align: 'center', valign: 'middle', sortable: true, formatter: sexFormatter},
        {title: '生日', field: 'birthday', align: 'center', valign: 'middle', sortable: true},
        {title: '角色', field: 'sysRoleName', align: 'center', valign: 'middle', sortable: true},
        {title: '部门', field: 'sysDeptName', align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '手机', field: 'mobile', align: 'center', valign: 'middle', sortable: true},
        {title: '邮箱', field: 'email', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: statusFormatter},
        {title: '创建人', field: 'createBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

/**
 * 性别判断
 * @param value
 * @param row
 * @param index
 */
function sexFormatter (value, row, index) {
    if (row['sex'] == 'm') {
        return '男';
    }
    if (row['sex'] == 'w') {
        return '女';
    }
    return value;
};

/**
 * 检查是否选中
 */
SysUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        SysUser.seItem = selected[0];
        return true;
    }
};

SysUser.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    SysUser.search();
}

SysUser.search = function () {
    var queryData = {};

    queryData['sysDeptId'] = SysUser.deptid;
    queryData['name'] = $("#name").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();

    SysUser.table.refresh({query: queryData});
}

SysUser.onClickDept = function (e, treeId, treeNode) {
    SysUser.deptid = treeNode.id;
    SysUser.search();
};

/**
 * 点击添加系统用户
 */
SysUser.openSaveSysUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统用户',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/sys-user/sysuser_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 系统用户id
 */
SysUser.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑系统用户',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/sys-user/sysuser_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
SysUser.delSysUser = function () {
    if (this.check()) {

        var operation = function(){
            var userId = SysUser.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/sys-user/remove", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                } else {
                    Feng.infoDetail("删除失败!", data.msg);
                }
                SysUser.table.refresh();
            }, function (data) {
                Feng.infoDetail("删除失败!", data.msg);
            });
            ajax.set("userId", userId);
            ajax.start();
        };

        Feng.confirm("是否删除用户" + SysUser.seItem.username + "?",operation);
    }
};

/**
 * 重置密码
 */
SysUser.resetPwd = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        parent.layer.confirm('是否重置密码为888888？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(Feng.ctxPath + "/api/sys-user/reset", function (data) {
                if (data.code == 200) {
                    Feng.success("重置密码成功!");
                } else {
                    Feng.infoDetail("重置密码失败!", data.msg);
                }
                SysUser.table.refresh();
            }, function (data) {
                Feng.infoDetail("重置密码失败!", data.msg);
            });
            ajax.set("userId", userId);
            ajax.start();
        });
    }
};

/**
 * 点击系统角色分配
 */
SysUser.roleAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '系统角色分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/sys-user/role_assign/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

$(function () {
    var defaultColunms = SysUser.initColumn();
    var table = new BSTable("sysUserTable", "/api/sys-user/sysuser_list", defaultColunms);
    table.setPaginationType("server");
    SysUser.table = table.init();
    var ztree = new $ZTree("deptTree", "/api/sys-dept/getDeptTree");
    ztree.bindOnClick(SysUser.onClickDept);
    ztree.init();
});