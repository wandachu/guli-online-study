package com.atguigu.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

  @Value("${aliyun.vod.file.keyid}")
  private String keyId;

  @Value("${aliyun.vod.file.keysecret}")
  private String keySecret;

  // public static constants
  public static String ACCESS_KEY_ID;
  public static String ACCESS_KEY_SECRET;

  @Override
  public void afterPropertiesSet() throws Exception {
    ACCESS_KEY_ID = this.keyId;
    ACCESS_KEY_SECRET = this.keySecret;
  }
}
