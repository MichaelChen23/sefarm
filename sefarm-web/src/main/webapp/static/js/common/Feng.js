var Feng = {
    ctxPath: "",
    addCtx: function (ctx) {
        if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
    },
    confirm: function (tip, ensure) {//询问框
        parent.layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            parent.layer.close(index);
        }, function (index) {
            parent.layer.close(index);
        });
    },
    log: function (info) {
        console.log(info);
    },
    alert: function (info, iconIndex) {
        parent.layer.msg(info, {
            icon: iconIndex
        });
    },
    info: function (info) {
        Feng.alert(info, 0);
    },
    success: function (info) {
        Feng.alert(info, 1);
    },
    error: function (info) {
        Feng.alert(info, 2);
    },
    infoDetail: function (title, info) {
        var display = "";
        if (typeof info == "string") {
            display = info;
        } else {
            if (info instanceof Array) {
                for (var x in info) {
                    display = display + info[x] + "<br/>";
                }
            } else {
                display = info;
            }
        }
        parent.layer.open({
            title: title,
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['950px', '600px'], //宽高
            content: '<div style="padding: 20px;">' + display + '</div>'
        });
    },
    writeObj: function (obj) {
        var description = "";
        for (var i in obj) {
            var property = obj[i];
            description += i + " = " + property + ",";
        }
        layer.alert(description, {
            skin: 'layui-layer-molv',
            closeBtn: 0
        });
    },
    showInputTree: function (inputId, inputTreeContentId, leftOffset, rightOffset) {
        var onBodyDown = function (event) {
            if (!(event.target.id == "menuBtn" || event.target.id == inputTreeContentId || $(event.target).parents("#" + inputTreeContentId).length > 0)) {
                $("#" + inputTreeContentId).fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
            }
        };

        if(leftOffset == undefined && rightOffset == undefined){
            var inputDiv = $("#" + inputId);
            var inputDivOffset = $("#" + inputId).offset();
            $("#" + inputTreeContentId).css({
                left: inputDivOffset.left + "px",
                top: inputDivOffset.top + inputDiv.outerHeight() + "px"
            }).slideDown("fast");
        }else{
            $("#" + inputTreeContentId).css({
                left: leftOffset + "px",
                top: rightOffset + "px"
            }).slideDown("fast");
        }

        $("body").bind("mousedown", onBodyDown);
    },
    baseAjax: function (url, tip) {
        var ajax = new $ax(Feng.ctxPath + url, function (data) {
            Feng.success(tip + "成功!");
        }, function (data) {
            Feng.error(tip + "失败!" + data.responseJSON.message + "!");
        });
        return ajax;
    },
    changeAjax: function (url) {
        return Feng.baseAjax(url, "修改");
    },
    zTreeCheckedNodes: function (zTreeId) {
        var zTree = $.fn.zTree.getZTreeObj(zTreeId);
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        return ids.substring(1);
    },
    eventParseObject: function (event) {//获取点击事件的源对象
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        return $(obj);
    }
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
 * 把 时间戳 转换为yyyy-mm-dd hh:mm:ss 形式
 * add by mc 2018-4-14
 *
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function timeFormatter(value, row, index) {
    if (value && value.length !== 0) {
        var date = new Date(value);
        var year = date.getFullYear();
        var month = fixFormatter(date.getMonth() + 1);
        var day = fixFormatter(date.getDate());
        var hour = fixFormatter(date.getHours());
        var minute = fixFormatter(date.getMinutes());
        var second = fixFormatter(date.getSeconds());
        return year + '-' + month + '-' + day + " " + hour + ":" + minute + ":" + second;
    } else {
        return "";
    }
};

/**
 * Thu Aug 18 20:38:54 CST 2016，CST时间格式转换
 * 输出格式：yyyy-MM-dd HH:mm:ss
 * @param cstTimeStr
 * @returns {string}
 */
function cstTimeFormatter(cstTimeStr) {
    if(null == cstTimeStr || "" == cstTimeStr){
        return "";
    }
    var dateStr = cstTimeStr.trim().split(" ");
    var gmtTimeStr = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";
    return timeFormatter(Date.parse(gmtTimeStr));
}


/**
 * fix 0 before format 不满10补0函数
 * @param value
 * @returns {string}
 */
function fixFormatter(value) {
    return value < 10 ? ('0' + value) : value;
};


