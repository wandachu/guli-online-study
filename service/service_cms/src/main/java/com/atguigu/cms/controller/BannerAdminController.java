package com.atguigu.cms.controller;


import com.atguigu.cms.entity.CrmBanner;
import com.atguigu.cms.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/cms/banneradmin")
@CrossOrigin
public class BannerAdminController {
  private final CrmBannerService bannerService;

  public BannerAdminController(CrmBannerService bannerService) {
    this.bannerService = bannerService;
  }

  @GetMapping("pageBanner/{page}/{limit}")
  public R pageBanner(@PathVariable long page, @PathVariable long limit) {
    Page<CrmBanner> pageBanner = new Page<>(page, limit);
    this.bannerService.page(pageBanner, null);
    return R.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
  }

  @PostMapping("addBanner")
  public R addBanner(@RequestBody CrmBanner banner) {
    this.bannerService.save(banner);
    return R.ok();
  }

  @PostMapping("update")
  public R updateById(@RequestBody CrmBanner banner) {
    this.bannerService.updateById(banner);
    return R.ok();
  }

  @DeleteMapping("remove/{id}")
  public R remove(@PathVariable String id) {
    this.bannerService.removeById(id);
    return R.ok();
  }

  @GetMapping("get/{id}")
  public R get(@PathVariable String id) {
    CrmBanner banner = this.bannerService.getById(id);
    return R.ok().data("item", banner);
  }
}

