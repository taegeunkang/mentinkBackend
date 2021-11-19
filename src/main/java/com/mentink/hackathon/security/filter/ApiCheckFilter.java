package com.mentink.hackathon.security.filter;

import com.mentink.hackathon.security.util.JWTUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ApiCheckFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String pattern;
    private JWTUtil jwtUtil;


    public ApiCheckFilter(String pattern, JWTUtil jwtUtil) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("RequestUri : "+ request.getRequestURI());

        log.info(String.valueOf(antPathMatcher.match(pattern, request.getRequestURI())));
        if(antPathMatcher.match(pattern, request.getRequestURI())) {
            log.info("ApiCheckFilter Worked!!!");
            log.info("ApiCheckFilter Worked!!!");
            log.info("ApiCheckFilter Worked!!!");

            boolean checkHeader =checkAuthHeader(request);

            if(checkHeader) {
                filterChain.doFilter(request,response);
                return;
            }else{
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                String message = "FAIL CHECK TOKEN";
                json.put("code", 403);
                json.put("message", message);
                PrintWriter out = response.getWriter();
                out.print(json);

                return;

            }


        }

        //다음 필터로 전달
        filterChain.doFilter(request,response);
    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            log.info("Authorization exist : " + authHeader);

            try {
                String username = jwtUtil.validateAndExtract(authHeader.substring(7));
                checkResult = username.length() > 0;

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return checkResult;
    }
}
