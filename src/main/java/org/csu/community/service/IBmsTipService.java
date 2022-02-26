package org.csu.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.community.model.entity.BmsBillboard;
import org.csu.community.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
