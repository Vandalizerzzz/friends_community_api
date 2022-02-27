package org.csu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.community.mapper.BmsPromotionMapper;
import org.csu.community.model.entity.BmsPromotion;
import org.csu.community.service.IBmsPromotionService;
import org.springframework.stereotype.Service;

@Service
public class IBmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper,
        BmsPromotion> implements IBmsPromotionService {

}
