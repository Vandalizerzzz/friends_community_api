package org.csu.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.csu.community.common.ApiResult;
import org.csu.community.model.entity.BmsBillboard;
import org.csu.community.model.entity.BmsPromotion;
import org.csu.community.service.IBmsBillboardService;
import org.csu.community.service.IBmsPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController {
    @Autowired
    private IBmsPromotionService bmsPromotionService;

    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> getPromotionList(){
        List<BmsPromotion> list = bmsPromotionService.list();
        return ApiResult.success(list);
    }
}
