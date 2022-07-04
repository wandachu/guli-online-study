package com.atguigu.cms.service;

import com.atguigu.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-13
 */
public interface CrmBannerService extends IService<CrmBanner> {

  List<CrmBanner> selectAllBanner();
}
