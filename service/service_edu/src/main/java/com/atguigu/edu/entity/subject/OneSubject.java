package com.atguigu.edu.entity.subject;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class OneSubject {
  private String id;
  private String title;
  private List<TwoSubject> children = new ArrayList<>();
}
