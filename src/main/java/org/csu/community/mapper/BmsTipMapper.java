package org.csu.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.community.model.entity.BmsTip;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    BmsTip getRandomTip();
}
