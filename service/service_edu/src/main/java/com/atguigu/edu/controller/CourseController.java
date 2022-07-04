package com.atguigu.edu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.vo.CourseInfoVo;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.atguigu.edu.entity.vo.CourseQuery;
import com.atguigu.edu.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/eduservice/course")
@CrossOrigin
public class CourseController {
  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping("addCourseInfo")
  public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
    String id = courseService.saveCourseInfo(courseInfoVo);
    return R.ok().data("courseId", id);
  }

  @GetMapping("getCourseInfo/{courseId}")
  public R getCourseInfo(@PathVariable String courseId) {
    CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
    return R.ok().data("courseInfoVo", courseInfoVo);
  }

  @PostMapping("updateCourseInfo")
  public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
    this.courseService.updateCourseInfo(courseInfoVo);
    return R.ok();
  }

  @GetMapping("getPublishCourseInfo/{id}")
  public R getPublishCourseInfo(@PathVariable String id) {
    CoursePublishVo coursePublishVo = this.courseService.publishCourseInfo(id);
    return R.ok().data("publishCourse", coursePublishVo);
  }

  @PostMapping("publishCourse/{id}")
  public R publishCourse(@PathVariable String id) {
    Course course = new Course();
    course.setId(id);
    course.setStatus("Normal"); // publish it
    boolean flag = this.courseService.updateById(course);
    return (flag) ? R.ok(): R.error();
  }

  @PostMapping("{current}/{limit}")
  public R getCourseList(@PathVariable long current, @PathVariable long limit,
      @RequestBody(required = false) CourseQuery courseQuery) {
    Page<Course> coursePage = new Page<>(current, limit);
    QueryWrapper<Course> wrapper = new QueryWrapper<>();
    String title = courseQuery.getTitle();
    String status = courseQuery.getStatus();

    if (!StringUtils.isEmpty(title)) {
      wrapper.like("title", title);
    }
    if (!StringUtils.isEmpty(status)) {
      wrapper.eq("status", status);
    }
    wrapper.orderByDesc("gmt_create"); // order by create time

    this.courseService.page(coursePage, wrapper);
    long total = coursePage.getTotal();
    List<Course> list = coursePage.getRecords();
    return R.ok().data("list", list).data("total", total);
  }

  @DeleteMapping("{courseId}")
  public R deleteCourse(@PathVariable String courseId) {
    this.courseService.removeCourse(courseId);
    return R.ok();
  }
}

