package org.csu.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.community.model.dto.LoginDTO;
import org.csu.community.model.dto.RegisterDTO;
import org.csu.community.model.entity.UmsUser;

public interface IUmsUserService extends IService<UmsUser> {
    UmsUser register(RegisterDTO dto);

    /**
     *
     * @param username
     * @return
     */
    UmsUser getUserByUsername(String username);

    /**
     *
     * @param dto
     * @return
     */
    String login(LoginDTO dto);

}
