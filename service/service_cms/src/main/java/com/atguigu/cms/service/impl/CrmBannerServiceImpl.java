package com.atguigu.cms.service.impl;

import com.atguigu.cms.entity.CrmBanner;
import com.atguigu.cms.mapper.CrmBannerMapper;
import com.atguigu.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author WandaWang
 * @since 2022-06-13
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

  @Override
  public List<CrmBanner> selectAllBanner() {
    return baseMapper.selectList(null);
  }
}
