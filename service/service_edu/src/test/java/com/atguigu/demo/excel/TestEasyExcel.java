package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

  public static void main(String[] args) {
//    // Test write into Excel
//    // 1. Set path and file name
    String fileName = "E:\\write.xlsx";
//
//    // 2. Use EasyExcel function
//    EasyExcel.write(fileName, DemoData.class).sheet("Student_List").doWrite(getData());

    // Test read from Excel
    EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();

  }

  private static List<DemoData> getData() {
    List<DemoData> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      DemoData data = new DemoData();
      data.setSno(i);
      data.setSname("Lucy" + i);
      list.add(data);
    }
    return list;
  }
}
