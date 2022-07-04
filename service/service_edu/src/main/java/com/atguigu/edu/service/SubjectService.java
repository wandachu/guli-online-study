package com.atguigu.edu.service;

import com.atguigu.edu.entity.Subject;
import com.atguigu.edu.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-02
 */
public interface SubjectService extends IService<Subject> {

  void saveSubject(MultipartFile file, SubjectService subjectService);

  List<OneSubject> getAllOneTwoSubject();
}
