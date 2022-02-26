package org.csu.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.csu.community.common.ApiResult;
import org.csu.community.model.entity.BmsBillboard;
import org.csu.community.model.entity.BmsTip;
import org.csu.community.service.IBmsBillboardService;
import org.csu.community.service.IBmsTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController {
    @Autowired
    private IBmsTipService bmsTipService;

    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip(){
        BmsTip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }
}
