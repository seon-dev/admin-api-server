package server.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();

        // authenticated path 인가
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/admin/health-check").permitAll()
//                .mvcMatchers(HttpMethod.POST, "/admin/auth/verify").permitAll()
                .mvcMatchers(HttpMethod.POST, "/admin/auth/logout").permitAll()
                .mvcMatchers(HttpMethod.POST, "/admin/permission/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/admin/permission/**").hasRole("ADMIN")
                .antMatchers("/admin/moderator/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/admin/permission/**").access("hasRole('ADMIN') or hasRole('MODERATOR')")//
                .mvcMatchers(HttpMethod.POST,"/admin/auth/**").hasAnyRole("ADMIN","MODERATOR")//유저생성: 어드민,모더레이터만 가능
//                .access("hasRole('ADMIN') and hasRole('MODERATOR')")
//                .mvcMatchers(HttpMethod.PUT, "/admin/user/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/admin/user/**").hasRole("ADMIN")//update, delete: 어드민만 가능
                .mvcMatchers(HttpMethod.PATCH, "/admin/user/**").hasRole("ADMIN")
                .anyRequest().authenticated();
//
        //authenticated exception 권한예외처리
        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                        response.sendRedirect("/admin/auth/denied");//접근권한이없음을 알리는 페이지로 리다이렉트
                        throw new java.nio.file.AccessDeniedException("접근 권한이 없습니다.");
                    }
                });


        // jwt auth filter inject
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider,authService), UsernamePasswordAuthenticationFilter.class);
    }

    //jwtAuthenticationFilter 필터 안 타도록 설정해주기
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admin/auth/sign-in")
                .antMatchers("/admin/auth/refresh-token")
                .antMatchers("/admin/auth/verify")
                .antMatchers("/admin/health-check")
                .antMatchers("/swagger-ui.html", "/swagger-ui/**","/swagger-resources/**","/v2/api-docs", "/swagger/**", "/webjars/**", "/configuration/ui", "/configuration/security");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
