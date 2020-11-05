package com.dong.springsecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @Author caishaodong
 * @Date 2020-11-01 15:58
 * @Description
 **/
public class JwtTokenUtil {
    // token 前缀
    public static String TOKEN_PREFIX = "mytoken_";
    // 寻找证书文件
    private static InputStream INPUT_STREAM = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey PRIVATE_KEY = null;
    private static PublicKey PUBLIC_KEY = null;

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            // java key store 固定常量
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(INPUT_STREAM, "123456".toCharArray());
            // jwt 为 命令生成整数文件时的别名
            PRIVATE_KEY = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());
            PUBLIC_KEY = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateToken(String subject, int expirationSeconds, String salt) {
        String token = Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
                .signWith(SignatureAlgorithm.RS256, PRIVATE_KEY)
                .compact();
        return TOKEN_PREFIX + token;
    }

    public static String parseToken(String token, String salt) {
        String subject = null;
        token = token.substring(TOKEN_PREFIX.length());
        try {
            Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
                    .setSigningKey(PUBLIC_KEY)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }
}
