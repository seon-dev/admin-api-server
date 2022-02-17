package server.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import server.admin.auth.interceptor.RefreshValidateInterceptor;
import server.admin.service.auth.AuthService;
import server.admin.utils.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;
    // TODO: allowedOrigins("*") is insecure.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name()
                );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new RefreshValidateInterceptor(jwtTokenProvider, authService)).addPathPatterns("/admin/auth/refresh-token");
    }
}
