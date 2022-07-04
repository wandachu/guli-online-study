package com.atguigu.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CourseInfoVo {
  @ApiModelProperty(value = "课程ID")
  private String id;

  @ApiModelProperty(value = "课程讲师ID") // id used in another table
  private String teacherId;

  @ApiModelProperty(value = "二级分类ID") // id used in another table
  private String subjectId;

  @ApiModelProperty(value = "一级分类ID") // id used in another table
  private String subjectParentId;

  @ApiModelProperty(value = "课程标题")
  private String title;

  @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
  // 0.01
  private BigDecimal price;

  @ApiModelProperty(value = "总课时")
  private Integer lessonNum;

  @ApiModelProperty(value = "课程封面图片路径")
  private String cover;

  @ApiModelProperty(value = "课程简介") // linked to another table
  private String description;
}
