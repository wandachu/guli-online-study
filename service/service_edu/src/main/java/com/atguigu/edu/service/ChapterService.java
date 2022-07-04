package com.atguigu.edu.service;

import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
public interface ChapterService extends IService<Chapter> {

  List<ChapterVo> getChapterVideoByCourseId(String courseId);

  boolean deleteChapter(String chapterId);

  void removeChapterByCourseId(String courseId);
}
