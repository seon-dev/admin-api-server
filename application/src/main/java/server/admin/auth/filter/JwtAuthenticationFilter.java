package server.admin.auth.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import server.admin.model.user.entity.User;
import server.admin.utils.JwtTokenProvider;
import server.admin.service.auth.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.isTokenNonExpired(token)) {
            try {
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException e) {
                log.info("Authentication not found exception. " + e.getMessage());
            }
        } else if( token == null){
            throw new RuntimeException("token doesn't exist!");
        } else if( !jwtTokenProvider.isTokenNonExpired(token) ){
            throw new RuntimeException("invalid token!");
        }

        filterChain.doFilter(request, response);
        System.out.println("end filter");
    }


    private Authentication getAuthentication(String token) throws UsernameNotFoundException {
        UserDetails userDetails = authService.loadUserByUsername(jwtTokenProvider.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", jwtTokenProvider.getAuthentication(token));
        //밑에있는 주석은 잘못된 방법
//        UserDetails principal = new org.springframework.security.core.userdetails.User(jwtTokenProvider.getUserId(token), "plavcorp", jwtTokenProvider.getAuthentication(token).getAuthorities());
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,"", jwtTokenProvider.getAuthentication(token).getAuthorities());
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        return authenticationToken;

    }
}
