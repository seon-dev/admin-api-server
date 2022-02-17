package server.admin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import server.admin.model.auth.dto.JwtIdentityDto;
import server.admin.model.user.entity.User;
import server.admin.model.user.role.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token-valid-time}")
    private long tokenValidTime;
    @Value("${jwt.refreshToken-valid-time}")
    private long refreshTokenValidTime;

    private final String TOKEN_HEADER_NAME = "X-AUTH-TOKEN";
    private final String REFRESHTOKEN_HEADER_NAME = "REFRESH-TOKEN";

    public String createToken(Long userId, String nickname, UserRole role) {
        return generateToken(userId,nickname, role,tokenValidTime);
    }
    public String createRefreshToken(Long userId, String nickname, UserRole role){
        return generateToken(userId, nickname, role, refreshTokenValidTime);
    }
    public String generateToken(Long userId, String nickname, UserRole role, long expireTime){
        Claims identityClaims = Jwts.claims();
        identityClaims.put("userId",userId.toString());
        identityClaims.put("nickname",nickname);
        identityClaims.put("role",role);



        Date now = new Date();

        return Jwts.builder()
                .setClaims(identityClaims)
//                .claim("identity", identityClaims)
                .setExpiration(new Date(now.getTime() + expireTime))//유닉스타임으ㄹ ㅗ변경
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

//    public JwtIdentityDto getIdentity(String token) throws JsonProcessingException {
//        System.out.println(token);
//        JwtIdentityDto identity = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("identity", JwtIdentityDto.class);
////        System.out.println(identity.getNickname());
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(identity.toString(), JwtIdentityDto.class);
//    }


    public String getUserId(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId").toString();
    }

    public String getNickname(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("nickname").toString();
    }
    public String getRole(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role").toString();
    }

    public String resolveToken(HttpServletRequest request) {
        if (request.getHeader(TOKEN_HEADER_NAME) == null) {
            return null;
        }
        return request.getHeader(TOKEN_HEADER_NAME);
    }

    public String resolveRefreshToken(HttpServletRequest request){
        if(request.getHeader(REFRESHTOKEN_HEADER_NAME) == null){
            return null;
        }
        return request.getHeader(REFRESHTOKEN_HEADER_NAME);
    }

    public boolean isTokenExpired(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("expired jwt exception");
            return false;
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.info("unsupported jwt exception");
            return false;
        } catch (MalformedJwtException malformedJwtException) {
            log.info("malformed jwt exception");
            return false;
        } catch (SignatureException signatureException) {
            log.info("signature exception");
            return false;
        } catch (IllegalArgumentException illegalArgumentException) {
            log.info("illegal argument exception");
            return false;
        } catch (Exception e) {
            log.info("unknown exception");
            return false;
        }
    }

    public boolean validateToken(String token, User user) throws JsonProcessingException {
        final String tokenUserId = getUserId(token);
        return (tokenUserId.equals(user.getId().toString()) && isTokenExpired(token));
    }
}
