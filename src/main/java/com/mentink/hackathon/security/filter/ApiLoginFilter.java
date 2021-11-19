package com.mentink.hackathon.security.filter;

import com.mentink.hackathon.security.dto.MenteeAuthDTO;
import com.mentink.hackathon.security.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class ApiLoginFilter  extends AbstractAuthenticationProcessingFilter {
    private JWTUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);

        log.info("로그인 인증 성공: "+ authResult);

        //jwt토큰 발급
        String username = ((MenteeAuthDTO)authResult.getPrincipal()).getUsername();
        //인증 토큰
        String token = null;
        //리프레쉬 토큰
        String refreshToken = null;

        try {
            Map<String, String> tokens = jwtUtil.generateToken(username);
            token = tokens.get("token");
            refreshToken = tokens.get("refreshToken");

            response.setContentType("text/plain");
            response.addHeader("verify-token", token);
            response.addHeader("refresh-token", refreshToken);
            response.getOutputStream().write("token issued".getBytes(StandardCharsets.UTF_8));
            log.info(token);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        log.info("----------ApiLoginFilter!!-------------");
        log.info("로그인 시도");

        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        if(username == null || pwd == null) {
            throw new BadCredentialsException("아이디 또는 비밀번호는 공백일 수 없습니다. ");
        }

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, pwd);

        return getAuthenticationManager().authenticate(authToken);

    }
}
