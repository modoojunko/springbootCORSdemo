package com.zhuke.corsdemo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Component
public class CorsFilter implements Filter {
    private static final String[] ALLOWED_ORIGINS={
            //"https://www.baidu.com",
            "http://localhost:8080",
            "http://localhost:8090",
            "https://localhost:8080"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Set<String> allowedOrigins = new HashSet<String>(Arrays.asList(ALLOWED_ORIGINS));
        String originHeader = request.getHeader("Origin");
        if(allowedOrigins.contains(originHeader)){
            response.setHeader("Access-Control-Allow-Origin",originHeader);
        }else{
            response.setHeader("Access-Control-Allow-Origin","http://zhuke");
        }
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Method","POST,GET,PUT,DELETE");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept");
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
