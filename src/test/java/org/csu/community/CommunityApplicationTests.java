package org.csu.community;

import org.csu.community.model.entity.BmsBillboard;
import org.csu.community.service.IBmsBillboardService;
import org.csu.community.service.IUmsUserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@MapperScan("org.csu.community.mapper")
class CommunityApplicationTests {

    @Autowired
    IBmsBillboardService service;

    @Autowired
    IUmsUserService service2;
    @Test
    void contextLoads() {
        service2.getUserByUsername("aaa");
    }

}
