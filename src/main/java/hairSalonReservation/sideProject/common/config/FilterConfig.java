package hairSalonReservation.sideProject.common.config;

import hairSalonReservation.sideProject.common.exception.ExceptionHandlerFilter;
import hairSalonReservation.sideProject.common.jwt.JwtFilter;
import hairSalonReservation.sideProject.common.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilter() {
        FilterRegistrationBean<ExceptionHandlerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ExceptionHandlerFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(jwtUtil));
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}