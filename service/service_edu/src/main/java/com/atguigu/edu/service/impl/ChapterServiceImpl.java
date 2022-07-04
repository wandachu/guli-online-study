package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.Video;
import com.atguigu.edu.entity.chapter.ChapterVo;
import com.atguigu.edu.entity.chapter.VideoVo;
import com.atguigu.edu.mapper.ChapterMapper;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
  private final VideoService videoService;

  public ChapterServiceImpl(VideoService videoService) {
    this.videoService = videoService;
  }

  @Override
  public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
    // Get all chapters
    QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("course_id", courseId);
    List<Chapter> chapterList = baseMapper.selectList(queryWrapper);

    // Get all videos
    QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
    videoWrapper.eq("course_id", courseId);
    List<Video> videoList = this.videoService.list(videoWrapper);

    // Add ChapterVo
    List<ChapterVo> finalList = new ArrayList<>();
    for (Chapter chapter : chapterList) {
      ChapterVo chapterVo = new ChapterVo();
      BeanUtils.copyProperties(chapter, chapterVo);
      finalList.add(chapterVo);

      // Add VideoVo
      List<VideoVo> videoVoList = new ArrayList<>();
      for (Video video : videoList) {
        if (video.getChapterId().equals(chapter.getId())) {
          VideoVo videoVo = new VideoVo();
          BeanUtils.copyProperties(video, videoVo);
          videoVoList.add(videoVo);
        }
      }
      chapterVo.setChildren(videoVoList);
    }
    return finalList;
  }

  @Override
  public boolean deleteChapter(String chapterId) {
    QueryWrapper<Video> wrapper = new QueryWrapper<>();
    wrapper.eq("chapter_id", chapterId);
    int count = this.videoService.count(wrapper);
    if (count > 0) { // has children videos, don't delete
      throw new GuliException(20001, "Cannot delete chapter that has videos");
    }
    int result = baseMapper.deleteById(chapterId);
    return result > 0; // if successful, result is 1
  }

  @Override
  public void removeChapterByCourseId(String courseId) {
    QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
    wrapper.eq("course_id", courseId);
    baseMapper.delete(wrapper);
  }
}
