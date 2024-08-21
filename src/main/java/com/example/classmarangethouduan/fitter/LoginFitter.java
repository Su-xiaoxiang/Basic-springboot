package com.example.classmarangethouduan.fitter;

import com.alibaba.fastjson.JSONObject;
import com.example.classmarangethouduan.pojo.Result;
import com.example.classmarangethouduan.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFitter implements Filter {

    private static final String LOGIN_URL = "login";
    private static final String REGISTER_URL = "register";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 强制转换
        HttpServletRequest sreq = (HttpServletRequest) servletRequest;
        HttpServletResponse srep = (HttpServletResponse) servletResponse;

        // 获取请求的 URL
        String url = sreq.getRequestURL().toString();
        log.info("获取到的 URL 为: " + url);

        // 判断是否为登录或注册操作
        if (url.contains(LOGIN_URL) || url.contains(REGISTER_URL)) {
            log.info("登录或注册操作...成功放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断是否含有 token
        String token = sreq.getHeader("Access-Token");
        if (!StringUtils.hasLength(token)) {
            log.info("Token 不正确或为空, 返回登录页面");
            sendErrorResponse(srep, "请重新登录");
            return;
        }

        // 使用 try-catch 解析 token
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("Token 解析失败");
            sendErrorResponse(srep, "请重新登录");
            return;
        }

        // 解析成功后进行放行
        log.info("Token 验证成功，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 发送错误响应
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置状态码为 401
        Result error = Result.error(message);
        String json = JSONObject.toJSONString(error);
        response.getWriter().write(json);
    }
}
