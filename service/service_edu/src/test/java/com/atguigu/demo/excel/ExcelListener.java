package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {

  // Read line by line of data (no headers included)
  @Override
  public void invoke(DemoData demoData, AnalysisContext analysisContext) {
    System.out.println("*** " + demoData);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    System.out.println("Headers: " + headMap);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
