package com.atguigu.edu.entity.chapter;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ChapterVo {
  private String id;
  private String title;
  List<VideoVo> children = new ArrayList<>();
}
