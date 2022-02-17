package server.admin.auth.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import server.admin.model.user.entity.User;
import server.admin.service.auth.AuthService;
import server.admin.utils.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshValidateInterceptor implements HandlerInterceptor {
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private final AuthService authService;
//    @Autowired
//    private final RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RefreshValidateInterceptor");
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        try {
            if (refreshToken != null) {
                String refreshNickame = jwtTokenProvider.getNickname(refreshToken);
                Long userId = Long.parseLong(jwtTokenProvider.getUserId(refreshToken));
                if (authService.existsRefreshToken(refreshNickame, userId)) {
                    User user = authService.loadUserByNickname(refreshNickame, userId);
                    if (jwtTokenProvider.validateToken(refreshToken, user)) {
                        try {
                            Authentication authentication = getAuthentication(refreshToken);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } catch (UsernameNotFoundException e) {
                            log.info("username not found exception" + e.getMessage());
                            // response.addCookie(CookieUtils.removeCookie("X-AUTH-TOKEN"));
                            return false;
                        }
                    } else {
                        log.info("invalid token");
                        return false;
                    }
                } else {
                    log.info("refresh token not exist in database");
                    return false;
                }
            } else {
                log.info("refresh token is null");
                return false;
            }
        } catch (ExpiredJwtException e) {
            log.info("refreshToken is expired");
            return false;
        }
        return true;
    }

    private Authentication getAuthentication(String token) throws UsernameNotFoundException, JsonProcessingException {
        UserDetails userDetails = authService.loadUserByUsername(jwtTokenProvider.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
//    private boolean existsRefreshToken(String key) {
//        return redisService.getValues(key) != null;
//    }