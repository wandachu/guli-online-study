package com.atguigu.edu.service;

import com.atguigu.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
public interface VideoService extends IService<Video> {

  void removeVideoByCourseId(String courseId);
}
