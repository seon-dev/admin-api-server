package server.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import server.admin.auth.filter.JwtAuthenticationFilter;
import server.admin.model.user.role.UserRole;
import server.admin.utils.JwtTokenProvider;
import server.admin.service.auth.AuthService;

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
                .mvcMatchers(HttpMethod.POST,"/admin/auth/sign-up").permitAll()
//                .mvcMatchers(HttpMethod.GET, "/status").anonymous()
                .mvcMatchers(HttpMethod.GET,"/admin/auth/refresh-token").permitAll()
                .mvcMatchers(HttpMethod.POST,"/admin/auth/sign-up").hasRole("ADMIN")
                .anyRequest().authenticated();
//                .and()
//                .formLogin()
//                .loginPage("/admin/auth/sign-in");
        //authenticated exception 권한예외처리
        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

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

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(authService);
    }

}
