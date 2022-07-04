package com.atguigu.edu.service;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.vo.CourseInfoVo;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
public interface CourseService extends IService<Course> {

  String saveCourseInfo(CourseInfoVo courseInfoVo);

  CourseInfoVo getCourseInfo(String courseId);

  void updateCourseInfo(CourseInfoVo courseInfoVo);

  CoursePublishVo publishCourseInfo(String id);

  void removeCourse(String courseId);
}
