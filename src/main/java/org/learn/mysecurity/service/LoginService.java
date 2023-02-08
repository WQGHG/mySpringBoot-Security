package org.learn.mysecurity.service;

import org.learn.mysecurity.model.LoginUser;
import org.learn.mysecurity.model.User;
import org.learn.mysecurity.response.ResponseResult;
import org.learn.mysecurity.util.JwtUtil;
import org.learn.mysecurity.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by wqg on 2023/1/6.
 */
@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    public ResponseResult<Map<String, String>> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJwt(userId);
        // 将token存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);

        //把token相应给前端
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        return new ResponseResult<>(200, "登录成功", map);
    }

    public ResponseResult<Object> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult<>(200, "退出成功", null);

    }

}
