package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtils;
import com.atguigu.vod.utils.InitVodCliente;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

  private final VodService vodService;

  public VodController(VodService vodService) {
    this.vodService = vodService;
  }

  @PostMapping("uploadAliyunVideo")
  public R uploadAliyunVideo(MultipartFile file) {
    String videoId = this.vodService.uploadVideoAliyun(file);
    return R.ok().data("videoId", videoId);
  }

  @DeleteMapping("removeAliyunVideo/{id}")
  public R removeAliyunVideo(@PathVariable String id) {
    try {
      DefaultAcsClient client = InitVodCliente.initVodClient(
          ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
      DeleteVideoRequest request = new DeleteVideoRequest();
      request.setVideoIds(id);
      client.getAcsResponse(request);
      return R.ok();
    } catch (Exception e) {
      e.printStackTrace();
      throw new GuliException(20001, "Error when deleting aliyun video.");
    }
  }

  @DeleteMapping("delete-batch")
  public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
    this.vodService.removeMultiAliyunVideo(videoIdList);
    return R.ok();
  }
}
