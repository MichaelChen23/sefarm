/**
 * 多张图片images上传 工具类
 *
 * 约定：传进的参数是imageList的id, picker的id, viewImages的id, inputImages的id
 * 每次使用前要初始化init
 *
 * @author mc
 * @date 2018-5-2
 */
(function() {

    var $ImagesUpload = function (imageListId, pickerId, viewImagesId, inputImages) {
        this.imageListId = imageListId;
        this.pickerId = pickerId;
        this.viewImagesId = viewImagesId;
        this.inputImages = inputImages;
        ratio = window.devicePixelRatio || 1;
        //缩略图长宽
        this.thumbnailWidth = 100 * ratio;
        this.thumbnailHeight = 100 * ratio;
        this.uploadUrl = Feng.ctxPath + '/api/image/upload';
        this.swfUrl = Feng.ctxPath + '/static/css/plugins/webuploader/Uploader.swf';
        this.fileSizeLimit = 10 * 1024 * 1024;
        this.clearFlag = true;
    };

    $ImagesUpload.prototype = {
        /**
         * 初始化imagesUpload
         */
        init : function() {
            var uploader = this.create();
            this.bindEvent(uploader);
            //先判断inputImages有没有已经保存的图片
            if ($("#" + this.inputImages).val().replace(/(^s*)|(s*$)/g, "").length !=0) {
                //存在已经保存的图片就要显示出来
                var imgArray = $("#" + this.inputImages).val().split(",");
                for (var i = 0; i<imgArray.length; i++) {
                    var $img = $(
                        '<div class="file-item thumbnail">' +
                        '<img id="productImg" src="/images/' + imgArray[i] + '" width="108" height="108"/>' +
                        '<div class="info">' + imgArray[i] + '</div>' +
                        '</div>'
                    );

                    $("#" + this.viewImagesId).append( $img );
                }
            }
            return uploader;
        },

        /**
         * 创建imagesUpload对象
         */
        create : function() {
            var imagesUploader = WebUploader.create({
                auto: true,
                swf: this.swfUrl,
                server: this.uploadUrl,
                pick: '#' + this.pickerId,
                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                },
                fileSingleSizeLimit: this.fileSizeLimit
            });
            return imagesUploader;
        },

        /**
         * 绑定事件
         */
        bindEvent : function(bindedObj) {
            var me = this;
            //显示图片预览list
            bindedObj.on( 'fileQueued', function( file ) {
                var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '</div>'
                    ),
                    $img = $li.find('img');

                $("#" + me.imageListId).append( $li );

                //生产缩略图
                bindedObj.makeThumb( file, function( error, src ) {
                    if ( error ) {
                        $img.replaceWith('<span>不能预览</span>');
                        return;
                    }

                    $img.attr( 'src', src );
                }, me.thumbnailWidth, me.thumbnailHeight );
            });

            //显示上传图片进度条
            bindedObj.on( 'uploadProgress', function( file, percentage ) {
                var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress span');

                if ( !$percent.length ) {
                    $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $li )
                        .find('span');
                }

                $percent.css( 'width', percentage * 100 + '%' );
            });

            //上传成功之后，保存上传后图片名到images保存，多张图片用“,”隔开
            bindedObj.on( 'uploadSuccess', function( file, response ) {
                $( '#'+file.id ).addClass('upload-state-done');
                Feng.success("上传成功");
                if (me.clearFlag) {
                    // 只需要在上传成功的第一次清除
                    // 清除imageView显示已经上传的图片
                    $("#" + me.viewImagesId).empty();
                    // 清除已经上传的图片images
                    $('#' + me.inputImages).val("");
                    // 停止更新
                    me.clearFlag = false;
                }
                // 获取成功上传文件的路径
                var imageUrl = response._raw;
                var imagesUrl = $('#' + me.inputImages).val();
                if (imagesUrl.replace(/(^s*)|(s*$)/g, "").length !=0) {
                    $('#' + me.inputImages).val(imagesUrl + "," + imageUrl);
                } else {
                    $('#' + me.inputImages).val(imageUrl);
                }
            });

            //上传失败
            bindedObj.on( 'uploadError', function( file ) {
                var $li = $( '#'+file.id ),
                    $error = $li.find('div.error');

                if ( !$error.length ) {
                    $error = $('<div class="error"></div>').appendTo( $li );
                }

                $error.text('上传失败');
            });

            //上传完成
            bindedObj.on( 'uploadComplete', function( file ) {
                $( '#'+file.id ).find('.progress').remove();
            });
        }
    };

    window.$ImagesUpload = $ImagesUpload;

}());