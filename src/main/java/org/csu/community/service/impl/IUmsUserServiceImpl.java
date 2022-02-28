package org.csu.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.csu.community.common.exception.ApiAsserts;
import org.csu.community.mapper.UmsUserMapper;
import org.csu.community.model.dto.LoginDTO;
import org.csu.community.model.dto.RegisterDTO;
import org.csu.community.model.entity.UmsUser;
import org.csu.community.service.IUmsUserService;
import org.csu.community.utils.JwtUtil;
import org.csu.community.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.util.Date;



@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser>
        implements IUmsUserService {//extends ServiceImpl是为了在该类中无需注入而直接使用BaseMapper


    @Override
    public UmsUser register(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsUser::getUsername, dto.getName()).or().eq(UmsUser::getEmail, dto.getEmail());
        UmsUser umsUser = baseMapper.selectOne(wrapper);
        try {
            if (!ObjectUtils.isEmpty(umsUser)) {
                ApiAsserts.fail("账号或邮箱已存在！");//
            }
        }catch (Exception e){
            log.warn("注册账号或邮箱已存在！");
            return null;
        }
        UmsUser addUser = UmsUser.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        return addUser;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>()
        .eq(UmsUser::getUsername,username));
    }

    @Override
    public String login(LoginDTO dto) {
        String token = null;
        try {
            UmsUser user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());

            if (!user.getPassword().equals(encodePwd)) {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(user.getUsername());
        }catch (Exception e){
            log.warn("用户名不存在or密码验证错误");
        }
        return token;
    }


}