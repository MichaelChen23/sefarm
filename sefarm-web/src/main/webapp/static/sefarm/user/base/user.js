/**
 * 用户 管理的单例
 */
var User = {
    id: "userTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
User.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '手机号码', field: 'mobile', align: 'center', valign: 'middle', sortable: true},
        {title: '昵称', field: 'nickname', align: 'center', valign: 'middle', sortable: true},
        {title: 'openid', field: 'openid', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'sex', align: 'center', valign: 'middle', sortable: true, formatter: sexFormatter},
        {title: '头像', field: 'headimgurl', align: 'center', valign: 'middle', sortable: true, formatter: headImageFormatter},
        {title: '国家', field: 'country', align: 'center', valign: 'middle', sortable: true},
        {title: '省份', field: 'province', align: 'center', valign: 'middle', sortable: true},
        {title: '城市', field: 'city', align: 'center', valign: 'middle', sortable: true},
        {title: '语言', field: 'language', align: 'center', valign: 'middle', sortable: true},
        {title: 'accessToken', field: 'accessToken', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '账号类型', field: 'accountType', align: 'center', valign: 'middle', sortable: true, formatter: accountTypeFormatter},
        {title: '等级', field: 'rank', align: 'center', valign: 'middle', sortable: true},
        {title: '余额', field: 'amount', align: 'center', valign: 'middle', sortable: true},
        {title: '积分', field: 'score', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', align: 'center', valign: 'middle', sortable: true, formatter: statusFormatter},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '最后登录时间', field: 'lastLoginTime', align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter},
        {title: '更新人', field: 'updateBy', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle', sortable: true, formatter: timeFormatter}
    ];
    return columns;
};

/**
 * 性别判断
 * @param value
 * @param row
 * @param index
 */
function sexFormatter(value, row, index) {
    if (row['sex'] == 1) {
        return '男';
    }
    if (row['sex'] == 2) {
        return '女';
    }
    if (row['sex'] == 0) {
        return '未知';
    }
    return value;
};

/**
 * 头像判断
 * @param value
 * @param row
 * @param index
 */
function headImageFormatter(value, row, index){
    if (value != null && value != "") {
        return "<img src='"+ value + "' width='88' height='88'/>";
    }
};

/**
 * 用户类型判断
 * @param value
 * @param row
 * @param index
 */
function accountTypeFormatter(value, row, index) {
    if (row['accountType'] == 'wechat') {
        return '微信用户';
    }
    if (row['accountType'] == 'mobile') {
        return '手机号用户';
    }
    return value;
};

/**
 * 检查是否选中
 */
User.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        User.seItem = selected[0];
        return true;
    }
};

User.resetSearch = function () {
    $("#name").val("");
    $("#mobile").val("");
    $("#address").val("");
    $("#sexInt").val("");
    $("#status").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    $("#loginBeginTime").val("");
    $("#loginEndTime").val("");

    User.search();
};


User.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['address'] = $("#address").val();
    queryData['sexInt'] = $("#sexInt").val();
    queryData['status'] = $("#status").val();
    queryData['createTimeBegin'] = $("#beginTime").val();
    queryData['createTimeEnd'] = $("#endTime").val();
    queryData['lastLoginTimeBegin'] = $("#loginBeginTime").val();
    queryData['lastLoginTimeEnd'] = $("#loginEndTime").val();

    User.table.refresh({query: queryData});
};

/**
 * 点击添加 用户
 */
User.openSaveUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户',
        area: ['800px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/api/user/user_save'
    });
    this.layerIndex = index;
};

/**
 * 点击修改 用户
 * @param userId 用户id
 */
User.openUpdateUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑用户',
            area: ['800px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/api/user/user_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 用户
 */
User.delUser = function () {
    if (this.check()) {

        var operation = function(){
            var userId = User.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/api/user/removeUser", function () {
                Feng.success("删除成功!");
                User.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", userId);
            ajax.start();
        };

        Feng.confirm("是否删除该昵称为：" + User.seItem.nickname + "的用户?", operation);
    }
};

$(function () {
    var defaultColunms = User.initColumn();
    var table = new BSTable("userTable", "/api/user/user_list", defaultColunms);
    table.setPaginationType("server");
    User.table = table.init();
});