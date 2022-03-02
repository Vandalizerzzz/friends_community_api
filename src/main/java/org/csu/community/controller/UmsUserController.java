package org.csu.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.csu.community.common.ApiResult;
import org.csu.community.model.dto.LoginDTO;
import org.csu.community.model.dto.RegisterDTO;
import org.csu.community.model.entity.UmsUser;
import org.csu.community.service.IUmsUserService;
import org.csu.community.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController {
    @Autowired
    IUmsUserService userService;

    @PostMapping("/register")
    public ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO dto){
        UmsUser user = userService.register(dto);
        System.out.println("注册执行了");
        if(ObjectUtils.isEmpty(user)){
            return ApiResult.failed("账号注册失败，请检查用户名或邮箱是否被使用过");
        }

        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);//前端没有用到该返回值
    }

    @PostMapping("/login")
    public ApiResult<Map<String,String>> login(@Valid @RequestBody LoginDTO dto){
        String token = userService.login(dto);
        if (ObjectUtils.isEmpty(token)){
            return ApiResult.failed("用户名或密码错误");
        }

        Map<String,String> map = new HashMap<>(16);
        map.put("token",token);
        return ApiResult.success(map,"登录成功");
    }

    @GetMapping("/info")
    public ApiResult<UmsUser> getUser(@RequestHeader(JwtUtil.USER_NAME) String userName){
        UmsUser user = userService.getOne(new LambdaQueryWrapper<UmsUser>()
        .eq(UmsUser::getUsername,userName));

        System.out.println();
        return ApiResult.success(user);
    }
}
