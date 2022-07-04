package com.atguigu.edu.client;

import com.atguigu.commonutils.R;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

  @DeleteMapping("/eduvod/video/removeAliyunVideo/{id}") // copied from VodController
  R removeAliyunVideo(@PathVariable("id") String id);

  @DeleteMapping("/eduvod/video/delete-batch")
  R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
