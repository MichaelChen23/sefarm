package com.sefarm.controller.common;

import com.google.code.kaptcha.Producer;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.util.DateUtil;
import com.sefarm.common.util.FileUtil;
import com.sefarm.config.properties.SeFarmProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 图片 控制器
 *
 * @author mc
 * @date 2018-4-23
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Resource
    private SeFarmProperties seFarmProperties;

    /**
     * 图片处理类
     */
    @Autowired
    Producer producer;


    /**
     * 上传图片(上传到项目的uploadfile路径)
     */
    @RequestMapping( path = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {
        String pictureName = "image_" + DateUtil.getAllTime() + ".jpg";
        try {
            String fileSavePath = seFarmProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }



    /**
     * 显示返回的图片
     *
     * @author mc
     * @date 2018-4-23
     */
    @RequestMapping("/{pictureId}")
    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = seFarmProperties.getFileUploadPath() + pictureId + ".jpg";
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/img/webuploader.png");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
