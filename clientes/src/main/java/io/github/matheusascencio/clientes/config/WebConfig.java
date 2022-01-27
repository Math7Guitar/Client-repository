package io.github.matheusascencio.clientes.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        List<String> all = Arrays.asList("*");
        CorsConfiguration corsConf = new CorsConfiguration();
        corsConf.setAllowedOrigins(all);
        corsConf.setAllowedHeaders(all);  
        corsConf.setAllowedMethods(all);
        corsConf.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConf);
        CorsFilter cF = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(cF);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }
}
