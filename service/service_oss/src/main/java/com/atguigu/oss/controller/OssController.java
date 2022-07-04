package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

  private final OssService ossService;

  public OssController(OssService ossService) {
    this.ossService = ossService;
  }

  @PostMapping
  public R uploadOssFile(MultipartFile file) {
    String url = ossService.uploadFileAvatar(file);
    return R.ok().data("url", url);
  }

}
