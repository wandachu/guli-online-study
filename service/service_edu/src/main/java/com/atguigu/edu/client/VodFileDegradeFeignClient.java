package com.atguigu.edu.client;

import com.atguigu.commonutils.R;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class VodFileDegradeFeignClient implements VodClient {

  @Override
  public R removeAliyunVideo(String id) {
    return R.error().message("time out when deleting aliyun video");
  }

  @Override
  public R deleteBatch(List<String> videoIdList) {
    return R.error().message("time out when deleting multiple aliyun video");
  }
}
