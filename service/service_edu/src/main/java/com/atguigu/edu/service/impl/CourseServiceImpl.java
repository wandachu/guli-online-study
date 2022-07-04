package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.CourseDescription;
import com.atguigu.edu.entity.vo.CourseInfoVo;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.atguigu.edu.mapper.CourseMapper;
import com.atguigu.edu.service.ChapterService;
import com.atguigu.edu.service.CourseDescriptionService;
import com.atguigu.edu.service.CourseService;
import com.atguigu.edu.service.VideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
  private final CourseDescriptionService courseDescriptionService;
  private final ChapterService chapterService;
  private final VideoService videoService;

  public CourseServiceImpl(CourseDescriptionService courseDescriptionService,
      ChapterService chapterService, VideoService videoService) {
    this.courseDescriptionService = courseDescriptionService;
    this.chapterService = chapterService;
    this.videoService = videoService;
  }

  @Override
  public String saveCourseInfo(CourseInfoVo courseInfoVo) {
    // 1. course table
    Course course = new Course();
    BeanUtils.copyProperties(courseInfoVo, course);
    int insert = baseMapper.insert(course);
    if (insert <= 0) {
      throw new GuliException(20001, "Error when adding the new course");
    } else {
      String courseId = course.getId();
      // 2. edu_course_description table
      CourseDescription courseDescription = new CourseDescription();
      courseDescription.setDescription(courseInfoVo.getDescription());
      courseDescription.setId(courseId); // manually set id to make them match
      courseDescriptionService.save(courseDescription);
      return courseId;
    }
  }

  @Override
  public CourseInfoVo getCourseInfo(String courseId) {
    CourseInfoVo courseInfoVo = new CourseInfoVo();
    // Find in course table
    Course course = baseMapper.selectById(courseId);
    BeanUtils.copyProperties(course, courseInfoVo);
    // Find in course_description table
    CourseDescription courseDescription = this.courseDescriptionService.getById(courseId);
    if (courseDescription == null) { // guard against unmatched database
      throw new GuliException(20001, "Course description for this course doesn't exist.");
    }
    courseInfoVo.setDescription(courseDescription.getDescription());
    return courseInfoVo;
  }

  @Override
  public void updateCourseInfo(CourseInfoVo courseInfoVo) {
    Course course = new Course();
    BeanUtils.copyProperties(courseInfoVo, course);
    int updateById = baseMapper.updateById(course);
    if (updateById == 0) {
      throw new GuliException(20001, "Error when updating course.");
    }
    CourseDescription description = new CourseDescription();
    description.setId(courseInfoVo.getId());
    description.setDescription(courseInfoVo.getDescription());
    this.courseDescriptionService.updateById(description);
  }

  @Override
  public CoursePublishVo publishCourseInfo(String id) {
    CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
    return publishCourseInfo;
  }

  @Override
  public void removeCourse(String courseId) {
    // First delete the lessons(videos)
    this.videoService.removeVideoByCourseId(courseId);
    // Then delete chapters
    this.chapterService.removeChapterByCourseId(courseId);
    // Next, delete the course description
    this.courseDescriptionService.removeById(courseId);
    // Delete the course itself
    int count = baseMapper.deleteById(courseId);
    if (count == 0) {
      throw new GuliException(20001, "Error when deleting course " + courseId);
    }

  }
}
