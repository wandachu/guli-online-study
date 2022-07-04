package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.entity.excel.SubjectData;
import com.atguigu.edu.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
  // This class cannot be handed to Spring to manage. Hence, we need to manually pass in subjectService.

  public SubjectService subjectService;

  public SubjectExcelListener() {
  }

  public SubjectExcelListener(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @Override
  public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
    if (subjectData == null) {
      throw new GuliException(20001, "File data is empty");
    }
    // Check and save oneSubject
    Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
    if (existOneSubject == null) { // this is the first one subject
      existOneSubject = new Subject();
      existOneSubject.setParentId("0");
      existOneSubject.setTitle(subjectData.getOneSubjectName());
      subjectService.save(existOneSubject);
    }

    // Check and save twoSubject
    String parentId = existOneSubject.getId();
    Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), parentId);
    if (existTwoSubject == null) {
      existTwoSubject = new Subject();
      existTwoSubject.setParentId(parentId);
      existTwoSubject.setTitle(subjectData.getTwoSubjectName());
      subjectService.save(existTwoSubject);
    }

  }

  // Make sure first subject cannot be added twice
  private Subject existOneSubject(SubjectService subjectService, String name) {
    QueryWrapper<Subject> wrapper = new QueryWrapper<>();
    wrapper.eq("title", name);
    wrapper.eq("parent_id", "0");
    return subjectService.getOne(wrapper);
  }

  private Subject existTwoSubject(SubjectService subjectService, String name, String parentId) {
    QueryWrapper<Subject> wrapper = new QueryWrapper<>();
    wrapper.eq("title", name);
    wrapper.eq("parent_id", parentId);
    return subjectService.getOne(wrapper);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
