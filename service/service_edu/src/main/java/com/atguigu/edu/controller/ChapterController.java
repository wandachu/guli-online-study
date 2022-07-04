package com.atguigu.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.entity.Chapter;
import com.atguigu.edu.entity.chapter.ChapterVo;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import java.util.List;
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
 * 课程 前端控制器
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class ChapterController {

  private final ChapterService chapterService;

  public ChapterController(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  @GetMapping("getChapterVideo/{courseId}")
  public R getChapterVideo(@PathVariable String courseId) {
    List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
    return R.ok().data("allChapterVideo", list);
  }

  @PostMapping("addChapter")
  public R addChapter(@RequestBody Chapter chapter) {
    if (StringUtils.isEmpty(chapter.getTitle())) {
      throw new GuliException(20001, "Chapter title cannot be empty");
    }
    this.chapterService.save(chapter);
    return R.ok();
  }

  @GetMapping("getChapterInfo/{chapterId}")
  public R getChapterInfo(@PathVariable String chapterId) {
    Chapter chapter = this.chapterService.getById(chapterId);
    return R.ok().data("chapter", chapter);
  }

  @PostMapping("updateChapter")
  public R updateChapter(@RequestBody Chapter chapter) {
    this.chapterService.updateById(chapter);
    return R.ok();
  }

  @DeleteMapping("{chapterId}")
  public R deleteChapter(@PathVariable String chapterId) {
    boolean flag = this.chapterService.deleteChapter(chapterId);
    return (flag) ? R.ok() : R.error().message("Error in deleting the chapter " + chapterId);
  }
}

