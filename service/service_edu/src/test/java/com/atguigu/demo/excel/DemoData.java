package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

  // Set excel headers
  @ExcelProperty(value = "Student Number", index = 0)
  private Integer sno;

  @ExcelProperty(value = "Student Name", index = 1)
  private String sname;
}
