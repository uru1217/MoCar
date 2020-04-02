package com.all.car.configration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

public class LocaleConfiguration {

    //bean 이름을 설정해주지 않으면 method이름과 같아 이름변경
    @Bean(name="localeResolver")
    public LocaleResolver getLocaleResover() {

        //세션 사용하려는경우
        //SessionLocaleResolver slr = new SessionLocaleResolver();
        //slr.setDefaultLocale(Locale.KOREAN);

        //쿠기기준(세션이 끊겨도 브라우저에 설정된 쿠키 기준으로
        //CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        //최초 기본 로케일을 강제로 설정이 가능하다.
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
        cookieLocaleResolver.setCookieName("APPLICATION_LOCALE");
        cookieLocaleResolver.setCookieMaxAge(60 * 60);

        return cookieLocaleResolver;
    }

    //Bean 이름설정
    @Bean(name = "messageSource")
    //매세지 소스 세팅
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:message/message");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setCacheSeconds(10);

        return messageResource;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//
//        registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/*");
//    }
}
