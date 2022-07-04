package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import java.io.InputStream;
import java.util.UUID;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OssServiceImpl implements OssService {

  @Override
  public String uploadFileAvatar(MultipartFile file) {
    String endpoint = ConstantPropertiesUtils.END_POINT;
    String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
    String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
    String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    String fileName = file.getOriginalFilename();

    try {
      InputStream inputStream = file.getInputStream();

      // Add unique part in the file name to avoid duplicate and override in oss
      String uuid = UUID.randomUUID().toString().replaceAll("-", "");
      fileName = uuid + fileName;

      // Add date in filename to get them sorted in different folders
      String datePath = new DateTime().toString("yyyy/MM/dd");
      fileName = datePath + "/" + fileName;

      ossClient.putObject(bucketName, fileName, inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (ossClient != null) {
        ossClient.shutdown();
      }
    }
    return "https://" + bucketName + "." + endpoint + "/" + fileName;
  }
}
