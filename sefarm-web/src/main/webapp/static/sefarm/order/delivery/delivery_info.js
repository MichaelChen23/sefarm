/**
 * 订单配送 详情对话框（可用于添加和修改对话框）
 */
var OrderDeliveryInfoDlg = {
    orderDeliveryInfoData: {}
};

/**
 * 清除数据
 */
OrderDeliveryInfoDlg.clearData = function () {
    this.orderDeliveryInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderDeliveryInfoDlg.set = function (key, val) {
    this.orderDeliveryInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderDeliveryInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrderDeliveryInfoDlg.close = function () {
    parent.layer.close(window.parent.OrderDelivery.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
OrderDeliveryInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#deptSelect").attr("value", instance.getSelectedVal());
    $("#sysDeptId").attr("value", treeNode.id);

    //查找部门下的系统用户要先清空sysUserId的所有option
    $("#sysUserId").empty();
    //获取所选部门下的所有系统用户
    $.ajax({
        url: '/api/sys-user/list_by_deptid',
        async: false,
        type: "POST",
        data: {
            "deptId": treeNode.id
        },
        success: function (data, status) {
            if (data != null) {
                $.each(data, function (key, value) {
                    var option = $("<option>").val(value.id).text(value.username);
                    $("#sysUserId").append(option);
                });
            }
        }
    });

};

/**
 * 显示部门选择的树
 *
 * @returns
 */
OrderDeliveryInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#deptSelect");
    var cityOffset = $("#deptSelect").offset();
    $("#deptContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
OrderDeliveryInfoDlg.hideDeptSelectTree = function () {
    $("#deptContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        OrderDeliveryInfoDlg.hideDeptSelectTree();
    }
}

/**
 * 收集数据
 */
OrderDeliveryInfoDlg.collectData = function() {
    this.set('id').set('orderId').set('sysDeptId').set('sysUserId').set('receiver').set('address').set('mobile').set('expressName')
        .set('expressNo').set('status').set('createTime').set('updateBy').set('updateTime').set('deliveryTime').set('receiveTime').set('remark');
};

/**
 * 提交添加 订单配送
 */
OrderDeliveryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/order-dely/save", function(data){
        Feng.success("添加成功!");
        window.parent.OrderDelivery.table.refresh();
        OrderDeliveryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderDeliveryInfoData);
    ajax.start();
};

/**
 * 提交修改 订单配送
 */
OrderDeliveryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/order-dely/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderDelivery.table.refresh();
        OrderDeliveryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderDeliveryInfoData);
    ajax.start();
};

$(function() {
    //初始化 部门树
    var ztree = new $ZTree("deptTree", "/api/sys-dept/getDeptTree");
    ztree.bindOnClick(OrderDeliveryInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    //初始化状态选项
    $("#status").val($("#statusValue").val());

    //获取所选部门下的所有系统用户
    $.ajax({
        url: '/api/sys-user/list_by_deptid',
        async: false,
        type: "POST",
        data: {
            "deptId": $("#sysDeptId").val()
        },
        success: function (data, status) {
            if (data != null) {
                $.each(data, function (key, value) {
                    var option = $("<option>").val(value.id).text(value.username);
                    $("#sysUserId").append(option);
                });
                //初始化 系统用户id
                $("#sysUserId").find("option[value=" + $("#sysUserIdValue").val() + "]").attr("selected", true);
            }
        }
    });

});

