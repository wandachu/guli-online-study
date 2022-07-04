package com.atguigu.edu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edu.entity.subject.OneSubject;
import com.atguigu.edu.service.SubjectService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-02
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {

  private final SubjectService subjectService;

  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @PostMapping("addSubject")
  public R addSubject(MultipartFile file) {
    subjectService.saveSubject(file, subjectService);
    return R.ok();
  }

  @GetMapping("getAllSubject")
  public R getAllSubject() {
    List<OneSubject> list = subjectService.getAllOneTwoSubject();
    return R.ok().data("list", list);
  }

}

