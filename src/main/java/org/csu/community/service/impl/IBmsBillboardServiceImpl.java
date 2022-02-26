package org.csu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.community.mapper.BmsBillBoardMapper;
import org.csu.community.model.entity.BmsBillboard;
import org.csu.community.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillBoardMapper,
        BmsBillboard> implements IBmsBillboardService {

}
