package com.mentink.hackathon.security.util;

import com.mentink.hackathon.domain.Logout;
import com.mentink.hackathon.repository.LogoutRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class JWTUtil {
    @Autowired
    private LogoutRepository logoutRepository;
    private String secretKey = "metinktothemars";
    //토큰 만기일 - 1
    private long expire = 60*24;
    //리프레쉬 토큰 만기일일 - 6개월
    private long refreshTokenExpire = 60*24*30*6;
    //토큰 갱신
    public String refresh(String content) throws Exception {
        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    //토큰 생성
    public Map<String, String> generateToken(String content) throws Exception {
        String token = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        String refreshToken = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(refreshTokenExpire).toInstant()))
                .claim("sub", content)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", token);
        tokens.put("refreshToken", refreshToken);

        return tokens;

    }

    public Date getExp(String tokenStr) {

        DefaultJws defaultJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(tokenStr);

        DefaultClaims defaultClaims = (DefaultClaims) defaultJws.getBody();
        Date exp = defaultClaims.getExpiration();

        return exp;
    }

    public String validateAndExtract(String tokenStr) throws Exception {
        String contentValue = null;
        Optional<Logout> logout = logoutRepository.findByToken(tokenStr);
        if(!logout.isPresent()) {
            try {
                DefaultJws defaultJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                        .parseClaimsJws(tokenStr);

                DefaultClaims defaultClaims = (DefaultClaims) defaultJws.getBody();
                contentValue = defaultClaims.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                contentValue = null;
            }
            return contentValue;
        }
        else{
            log.warn("this token cannot use");
            return null;
        }
    }


}
