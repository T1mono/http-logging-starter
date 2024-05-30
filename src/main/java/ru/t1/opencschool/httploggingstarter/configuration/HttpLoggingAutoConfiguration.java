package ru.t1.opencschool.httploggingstarter.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1.opencschool.httploggingstarter.filter.HttpLoggingFilter;

/**
 * Конфигурация, которая включает фильтр в процесс логирования.
 */
@Configuration
@ConditionalOnWebApplication
public class HttpLoggingAutoConfiguration {

    @Bean
    public FilterRegistrationBean<HttpLoggingFilter> httpLoggingFilterRegistrationBean() {
        FilterRegistrationBean<HttpLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        HttpLoggingFilter filter = new HttpLoggingFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(Integer.MIN_VALUE); // Установка порядка, чтобы убедиться, что фильтр применяется первым
        registrationBean.addUrlPatterns("/*"); // Установка шаблона URL-адресов, к которым следует применять фильтр

        return registrationBean;
    }
}
