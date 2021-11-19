package com.mentink.hackathon.security.config;

import com.mentink.hackathon.security.filter.ApiCheckFilter;
import com.mentink.hackathon.security.filter.ApiLoginFilter;
import com.mentink.hackathon.security.handler.ApiLoginFailHandler;
import com.mentink.hackathon.security.util.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/app/**/*", jwtUtil());
    }
    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/user/login", jwtUtil() );
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
        return apiLoginFilter;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/user/register");
    }

    /*
     security rule settings
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                        .antMatchers("/user/login/**").permitAll();

        http.csrf().disable();
        http.logout();
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService());

        //필터의 위치 조정
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
