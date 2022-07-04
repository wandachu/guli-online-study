package com.atguigu.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.entity.Teacher;
import com.atguigu.edu.entity.vo.TeacherQuery;
import com.atguigu.edu.service.TeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 讲师 前端控制器
 * </p>
 *
 * @author WandaWang
 * @since 2022-05-27
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class TeacherController {

  private final TeacherService teacherService;

  @Autowired
  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping("findAll")
  public R findAll() {
    List<Teacher> list = teacherService.list(null);
    return R.ok().data("items", list);
  }

  @DeleteMapping("{id}")
  public R removeTeacher(@PathVariable String id) {
    boolean flag = teacherService.removeById(id);
    return flag ? R.ok() : R.error();
  }

  @GetMapping("pageTeacher/{current}/{limit}")
  public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {
    Page<Teacher> pageTeacher = new Page<>(current, limit);
    teacherService.page(pageTeacher, null);
    long total = pageTeacher.getTotal();
    List<Teacher> records = pageTeacher.getRecords();
    return R.ok().data("total", total).data("rows", records);
  }

  @PostMapping("pageTeacherCondition/{current}/{limit}")
  public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                @RequestBody(required = false) TeacherQuery teacherQuery) {
    // Create Page and Wrapper object
    Page<Teacher> pageTeacher = new Page<>(current, limit);
    QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
    // Finish Wrapper
    String name = teacherQuery.getName();
    String begin = teacherQuery.getBegin();
    String end = teacherQuery.getEnd();
    Integer level = teacherQuery.getLevel();
    if (!StringUtils.isEmpty(name)) {
      wrapper.like("name", name);
    }
    if (!StringUtils.isEmpty(level)) {
      wrapper.eq("level", level);
    }
    if (!StringUtils.isEmpty(begin)) {
      wrapper.ge("gmt_create", begin);
    }
    if (!StringUtils.isEmpty(end)) {
      wrapper.le("gmt_create", end);
    }
    wrapper.orderByDesc("gmt_create"); // order by create time
    // Search and return R
    teacherService.page(pageTeacher, wrapper);
    long total = pageTeacher.getTotal();
    List<Teacher> records = pageTeacher.getRecords();
    return R.ok().data("total", total).data("rows", records);
  }

  @PostMapping("addTeacher")
  public R addTeacher(@RequestBody Teacher teacher) {
    boolean save = teacherService.save(teacher);
    return save ? R.ok() : R.error();
  }

  @GetMapping("getTeacher/{id}")
  public R getTeacher(@PathVariable String id) {
    Teacher teacher = teacherService.getById(id);
    return R.ok().data("teacher", teacher);
  }

  @PostMapping("updateTeacher")
  public R updateTeacher(@RequestBody Teacher teacher) {
    boolean flag = teacherService.updateById(teacher);
    return flag ? R.ok() : R.error();
  }

}

