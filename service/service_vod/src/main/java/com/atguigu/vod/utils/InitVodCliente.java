package com.atguigu.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

public class InitVodCliente {

  // Specify the AccessKey pair.
  public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
    String regionId = "cn-shanghai";  // Specify the region from which you want to access ApsaraVideo VOD.
    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
    DefaultAcsClient client = new DefaultAcsClient(profile);
    return client;
  }

}
