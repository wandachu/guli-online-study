package com.atguigu.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.client.VodClient;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.service.VideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {

  private final VideoService videoService;
  private final VodClient vodClient;

  public VideoController(VideoService videoService, VodClient vodClient) {
    this.videoService = videoService;
    this.vodClient = vodClient;
  }

  @PostMapping("addVideo")
  public R addVideo(@RequestBody Video video) {
    if (StringUtils.isEmpty(video.getTitle())) {
      throw new GuliException(20001, "Video title cannot be empty");
    }
    this.videoService.save(video);
    return R.ok();
  }

  @DeleteMapping("{id}")
  public R deleteVideo(@PathVariable String id) {
    Video video = this.videoService.getById(id); // Get aliyun video id and then pass in the id
    String videoSourceId = video.getVideoSourceId();
    if (!StringUtils.isEmpty(videoSourceId)) {
      R result = this.vodClient.removeAliyunVideo(videoSourceId);
      if (result.getCode() == 20001) { // use Hystrix. If error, throw Exception
        throw new GuliException(20001, "Error when deleting aliyun videos....Hystrix");
      }
    }
    // Remove the video (lesson)
    boolean flag = this.videoService.removeById(id);
    return (flag) ? R.ok() : R.error().message("Error in deleting the video " + id);
  }

  @PostMapping("updateVideo")
  public R updateChapter(@RequestBody Video video) {
    this.videoService.updateById(video);
    return R.ok();
  }

  @GetMapping("getVideoInfo/{videoId}")
  public R getVideoInfo(@PathVariable String videoId) {
    Video video = this.videoService.getById(videoId);
    return R.ok().data("video", video);
  }
}

