package server.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import server.admin.auth.filter.JwtAuthenticationFilter;
import server.admin.auth.handler.AuthHandler;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.user.role.UserRole;
import server.admin.utils.JwtTokenProvider;
import server.admin.service.auth.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //rest api
        http.httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // authenticated path 인가
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/health-check").anonymous()
                .antMatchers(HttpMethod.POST, "/admin/auth/sign-in/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/admin/auth/verify").permitAll()
//                .mvcMatchers(HttpMethod.GET,"/admin/auth/refresh-token").permitAll()
                .mvcMatchers(HttpMethod.POST,"/admin/auth/sign-up").hasAnyRole("ADMIN","MODERATOR")//유저생성: 어드민,모더레이터만 가능
                .mvcMatchers(HttpMethod.PUT, "/admin/user/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/admin/user/**").hasRole("MODERATOR")//update,delete: 어드민만 가능
                .anyRequest().authenticated();
//
        //authenticated exception 권한예외처리
        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/admin/auth/denied");//접근권한이없음을 알리는 페이지로 리다이렉트
                    }
                });


        // jwt auth filter inject
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, authService), UsernamePasswordAuthenticationFilter.class);
    }

    //jwtAuthenticationFilter 필터 안 타도록 설정해주기
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(HttpMethod.POST,"/admin/auth/sign-in")
                .mvcMatchers(HttpMethod.GET,"/admin/auth/refresh-token")
                .mvcMatchers(HttpMethod.POST, "/admin/auth/verify");

    }


}
