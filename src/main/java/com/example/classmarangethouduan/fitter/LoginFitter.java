package com.example.classmarangethouduan.fitter;

import com.example.classmarangethouduan.utils.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import com.example.classmarangethouduan.pojo.Result;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("----------------------------------进入过滤器------------------------------------------");
        HttpServletRequest sreq = (HttpServletRequest) servletRequest;
        HttpServletResponse srep = (HttpServletResponse) servletResponse;
        // 过滤 OPTIONS 请求
        if ("OPTIONS".equalsIgnoreCase(sreq.getMethod())) {
            log.info("OPTIONS 请求，直接放行");
            log.info("----------------------检测POTIONS请求完毕------------------------------------------");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String url = sreq.getRequestURL().toString();
        log.info("获取到的后端url为: " + url);

        if (url.contains("login") || url.contains("register")) {
            log.info("登录或注册操作...成功放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = sreq.getHeader("Authorization-Token");
        log.info("获取到的用户token: {}", token);
        if (!StringUtils.hasLength(token)) {
            log.info("token不正确或已经过期,返回登录页面");
            sendErrorResponse(srep, "失败验证，请重新登录");
            return;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("解析失败");
            sendErrorResponse(srep, "失败验证，请重新登录");
            return;
        }
        log.info("----------------------------------------------------");
        log.info("啦啦啦啦......验证成功放行");
        log.info("----------------------------------------------------");
        log.info("------------------过滤器结束--------------------------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Result error = Result.error(message);
        String json = JSONObject.toJSONString(error);
        response.getWriter().write(json);
    }
}
