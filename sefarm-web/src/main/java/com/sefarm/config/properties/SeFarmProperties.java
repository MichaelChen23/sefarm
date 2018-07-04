package com.sefarm.config.properties;

import com.sefarm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * SeFarm项目配置
 *
 * @author mc
 * @date 2018-3-30
 */
@Component
@ConfigurationProperties(prefix = SeFarmProperties.PREFIX)
public class SeFarmProperties {

    public static final String PREFIX = "sefarm";

    private Boolean kaptchaOpen = false;

    private Boolean swaggerOpen = false;

    private String fileUploadPath;

    private Boolean haveCreatePath = false;

    private Boolean springSessionOpen = false;

    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;

    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;

    /**
     * 微信的session 有效时间（默认2小时 单位：秒）
     * add by mc 2018-7-4
     */
    private Integer wechatSessionEffectTime = 2 * 60 * 60;

    public String getFileUploadPath() {
        //如果没有写文件上传路径,保存到临时目录
        if (StringUtils.isEmpty(fileUploadPath)) {
            return ToolUtil.getTempPath();
        } else {
            //判断有没有结尾符,没有得加上
            if (!fileUploadPath.endsWith(File.separator)) {
                fileUploadPath = fileUploadPath + File.separator;
            }
            //判断目录存不存在,不存在得加上
            if (haveCreatePath == false) {
                File file = new File(fileUploadPath);
                file.mkdirs();
                haveCreatePath = true;
            }
            return fileUploadPath;
        }
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public Boolean getKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public Boolean getSwaggerOpen() {
        return swaggerOpen;
    }

    public void setSwaggerOpen(Boolean swaggerOpen) {
        this.swaggerOpen = swaggerOpen;
    }

    public Boolean getSpringSessionOpen() {
        return springSessionOpen;
    }

    public void setSpringSessionOpen(Boolean springSessionOpen) {
        this.springSessionOpen = springSessionOpen;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public Integer getWechatSessionEffectTime() {
        return wechatSessionEffectTime;
    }

    public void setWechatSessionEffectTime(Integer wechatSessionEffectTime) {
        this.wechatSessionEffectTime = wechatSessionEffectTime;
    }
}
