package org.learn.mysecurity.handler;

import com.alibaba.fastjson.JSON;
import org.learn.mysecurity.response.ResponseResult;
import org.learn.mysecurity.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  授权失败处理
 * Created by wqg on 2023/2/8.
 */
@Component
public class AccessDeniedHandlerImpl  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
