package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.Course;
import com.atguigu.edu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-03
 */
public interface CourseMapper extends BaseMapper<Course> {
  public CoursePublishVo getPublishCourseInfo(String courseId);
}
