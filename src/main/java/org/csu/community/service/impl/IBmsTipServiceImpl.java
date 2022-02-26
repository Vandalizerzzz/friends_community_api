package org.csu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.csu.community.mapper.BmsBillBoardMapper;
import org.csu.community.mapper.BmsTipMapper;
import org.csu.community.model.entity.BmsBillboard;
import org.csu.community.model.entity.BmsTip;
import org.csu.community.service.IBmsBillboardService;
import org.csu.community.service.IBmsTipService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper,
        BmsTip> implements IBmsTipService {

    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        }catch (Exception e){
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
