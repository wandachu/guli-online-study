package com.atguigu.edu.service.impl;

import com.atguigu.edu.client.VodClient;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.mapper.VideoMapper;
import com.atguigu.edu.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

  private final VodClient vodClient;

  public VideoServiceImpl(VodClient vodClient) {
    this.vodClient = vodClient;
  }

  @Override
  public void removeVideoByCourseId(String courseId) {
    // Get the list of aliyun video under the course
    QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
    wrapperVideo.eq("course_id", courseId);
    wrapperVideo.select("video_source_id"); // no need other info
    List<Video> videoList = baseMapper.selectList(wrapperVideo);
    // Take all videoSourceId's, join them and pass to deleteBatch
    List<String> videoIds = videoList.stream().map(Video::getVideoSourceId)
        .filter(a -> !StringUtils.isEmpty(a)).collect(Collectors.toList());
    // Delete when size is > 0
    if (videoIds.size() > 0) {
      this.vodClient.deleteBatch(videoIds);
    }

    // Delete the video itself
    QueryWrapper<Video> wrapper = new QueryWrapper<>();
    wrapper.eq("course_id", courseId);
    baseMapper.delete(wrapper);
  }
}
