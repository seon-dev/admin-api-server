package server.admin.auth.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import server.admin.model.user.entity.User;
import server.admin.utils.JwtTokenProvider;
import server.admin.service.auth.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        User user = authService.loadUserByNickname(jwtTokenProvider.getNickname(token), Long.parseLong(jwtTokenProvider.getUserId(token)));
        if (token != null && jwtTokenProvider.validateToken(token, user)) {
            try {
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (UsernameNotFoundException e) {
                log.info("username not found exception" + e.getMessage());
                // response.addCookie(CookieUtils.removeCookie("X-AUTH-TOKEN"));
            }
        } else throw new RuntimeException("invalid token!");

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String token) throws UsernameNotFoundException, JsonProcessingException {
        UserDetails userDetails = authService.loadUserByUsername(jwtTokenProvider.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }
}
