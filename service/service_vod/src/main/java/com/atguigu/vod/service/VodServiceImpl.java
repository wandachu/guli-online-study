package com.atguigu.vod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.utils.ConstantPropertiesUtils;
import com.atguigu.vod.utils.InitVodCliente;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VodServiceImpl implements VodService {

  @Override
  public String uploadVideoAliyun(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    String title = fileName.substring(0, fileName.lastIndexOf("."));
    InputStream inputStream = null;
    try {
      inputStream = file.getInputStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
    UploadVideoImpl uploader = new UploadVideoImpl();
    UploadStreamResponse response = uploader.uploadStream(request);
    return response.getVideoId();
  }

  @Override
  public void removeMultiAliyunVideo(List videoIdList) {
    try {
      DefaultAcsClient client = InitVodCliente.initVodClient(
          ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
      DeleteVideoRequest request = new DeleteVideoRequest();

      String idListAsString = StringUtils.join(videoIdList.toArray(), ",");
      request.setVideoIds(idListAsString);
      client.getAcsResponse(request);
    } catch (Exception e) {
      e.printStackTrace();
      throw new GuliException(20001, "Error when deleting aliyun video.");
    }
  }
}
