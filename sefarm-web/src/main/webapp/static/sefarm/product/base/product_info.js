/**
 * 产品 详情对话框（可用于添加和修改对话框）
 */
var ProductInfoDlg = {
    productInfoData: {}
};

/**
 * 清除数据
 */
ProductInfoDlg.clearData = function () {
    this.productInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.set = function (key, val) {
    this.productInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 获取对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ProductInfoDlg.close = function () {
    parent.layer.close(window.parent.Product.layerIndex);
};

/**
 * 收集数据
 */
ProductInfoDlg.collectData = function() {
    this.set('id').set('name').set('introduce').set('price').set('nowPrice').set('picture').set('newFlag').set('saleFlag')
        .set('unit').set('sort').set('status').set('sellCount').set('stock').set('replyHit').set('goodHit').set('searchWord')
        .set('title').set('remark').set('productTypeId').set('productHtml').set('images').set('detail');
};

/**
 * 提交添加 产品
 */
ProductInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod/save", function(data){
        Feng.success("添加成功!");
        window.parent.Product.table.refresh();
        ProductInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productInfoData);
    ajax.start();
};

/**
 * 提交修改 产品
 */
ProductInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/api/prod/update", function(data){
        Feng.success("修改成功!");
        window.parent.Product.table.refresh();
        ProductInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productInfoData);
    ajax.start();
};

$(function() {

    //初始化状态选项
    $("#status").val($("#statusValue").val());
    $("#newFlag").val($("#newFlagValue").val());
    $("#saleFlag").val($("#saleFlagValue").val());

    // 初始化小图片上传
    var pictureUp = new $WebUpload("picture");
    pictureUp.init();

    // 初始化多图片上传
    var imagesUp = new $ImagesUpload("imageList", "imagePicker", "viewImages", "images");
    imagesUp.init();

    //获取所有的产品类型
    $.ajax({
        url: '/api/prod-type/getAll',
        async: false,
        type: "GET",
        data: {},
        success: function (data, status) {
            if (data.code == 200) {
                $.each(data.result, function (key, value) {
                    var option = $("<option>").val(value.id).text(value.name);
                    $("#productTypeId").append(option);
                });
                //初始化 产品类型id
                $("#productTypeId").find("option[value=" + $("#productTypeIdValue").val() + "]").attr("selected", true);
            }
        }
    });

});

