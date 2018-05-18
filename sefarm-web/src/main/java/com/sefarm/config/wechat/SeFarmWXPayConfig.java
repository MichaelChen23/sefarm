package com.sefarm.config.wechat;

import com.github.wxpay.sdk.WXPayConfig;
import com.sefarm.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 微信支付config配置
 * @author mc
 * @date 2018-5-18
 */
public class SeFarmWXPayConfig implements WXPayConfig {

    private static final Logger logger = LoggerFactory.getLogger(SeFarmWXPayConfig.class);

    private byte[] certData;

    public SeFarmWXPayConfig() throws Exception {
        String certPath = "/sefarm/cert/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }


    @Override
    public String getAppID() {
        return Constant.WECHAT_APPID;
    }

    @Override
    public String getMchID() {
        return Constant.WECHAT_MCH_ID;
    }

    @Override
    public String getKey() {
        return Constant.WECHAT_API_KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
