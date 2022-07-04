package com.atguigu.cms.controller;

import com.atguigu.cms.entity.CrmBanner;
import com.atguigu.cms.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/bannerfront")
@CrossOrigin
public class BannerFrontController {
  private final CrmBannerService bannerService;

  public BannerFrontController(CrmBannerService bannerService) {
    this.bannerService = bannerService;
  }

  @GetMapping("getAllBanner")
  public R getAllBanner() {
    List<CrmBanner> list =  this.bannerService.selectAllBanner();
    return R.ok().data("list", list);
  }

}
