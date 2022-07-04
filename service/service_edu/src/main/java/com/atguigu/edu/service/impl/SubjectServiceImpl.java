package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.entity.excel.SubjectData;
import com.atguigu.edu.entity.subject.OneSubject;
import com.atguigu.edu.entity.subject.TwoSubject;
import com.atguigu.edu.listener.SubjectExcelListener;
import com.atguigu.edu.mapper.SubjectMapper;
import com.atguigu.edu.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-02
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

  @Override
  public void saveSubject(MultipartFile file, SubjectService subjectService) {
    try {
      InputStream in = file.getInputStream();
      EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<OneSubject> getAllOneTwoSubject() {
    // find all OneSubjects (parentId == 0)
    QueryWrapper<Subject> wrapper1 = new QueryWrapper<>();
    wrapper1.eq("parent_id", "0");
    List<Subject> oneSubjectList = baseMapper.selectList(wrapper1);
    // find all TwoSubjects (parentId != 0)
    QueryWrapper<Subject> wrapper2 = new QueryWrapper<>();
    wrapper2.ne("parent_id", "0");
    List<Subject> twoSubjectList = baseMapper.selectList(wrapper2);
    // put them together
    List<OneSubject> finalSubjectList = new ArrayList<>();
    // Add oneSubject
    for (Subject subject : oneSubjectList) {
      OneSubject oneSubject = new OneSubject();
      BeanUtils.copyProperties(subject, oneSubject);
      finalSubjectList.add(oneSubject);

      // Add twoSubject to its parent
      List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
      for (Subject tSubject : twoSubjectList) {
        if (tSubject.getParentId().equals(oneSubject.getId())) {
          TwoSubject twoSubject = new TwoSubject();
          BeanUtils.copyProperties(tSubject, twoSubject);
          twoFinalSubjectList.add(twoSubject);
        }
      }
      oneSubject.setChildren(twoFinalSubjectList);
    }
    return finalSubjectList;
  }
}
