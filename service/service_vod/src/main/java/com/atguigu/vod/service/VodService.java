package com.atguigu.vod.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface VodService {

  String uploadVideoAliyun(MultipartFile file);

  void removeMultiAliyunVideo(List videoIdList);
}
