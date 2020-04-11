package com.all.car.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//클래스에 Spring Security 설정한 클래스라고 정의
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //WebSecurityConfigurerAdapter 를 상속받아 메서드를 구현하는것이 일반적이다.


    //Spring Security에서 제공하는 비밀번화 암호화 객체
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //WebSecurity를 이용하여 FilterChinProxy를 생성
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        //Spring security가 무시하여 static directory 하위 파일 목록은 항상 통과
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/plugin/**", "/sass/**");
    }

    @Override
    //HttpSecurity를 통해 HTTP요청에 대한 보안구성
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //authorizeRequests
        //antMatchers를  통해 특정 경로를 지정하여 역할에 다른 설정을 잡아준다.
        //.csrf().disable 토큰 사용하지않으려면
        httpSecurity.authorizeRequests()
                //v페이지 권한설정
//                .antMatchers("/index").permitAll()
//                .antMatchers("/map").permitAll()
//                .antMatchers("/board/list/**").permitAll()
//                .antMatchers("/board/get/**").permitAll()
                .antMatchers("/reply/**").permitAll()
//                .antMatchers("/board/write/**").hasRole("member")
//                .antMatchers("/board/modify/**").hasRole("member")
                .antMatchers("/**/**").permitAll()

                .and()
                //로그인 설정 , form 기반으로 인증하도록 하며 로그인정보는 기본적으로 HttpSession을 이용한다.
                .formLogin()
                //커스텀 로그인 폼을 사용하기 위해서는 loginPage()메서드 사용
                .loginPage("/memberlogin")
                //loginProcessingUrl()파라미터경로와 커스텀 로그인 form의 action 경로가 일치해야한다.
                .loginProcessingUrl("/logIn")
                //실패시
                .failureUrl("/login-error")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout() //로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/regOut"))
                .logoutSuccessUrl("/index")
                //HTTP세션을 초기화하는 직업
                .invalidateHttpSession(true)
                //특정쿠키 제거시
                //.deleteCookies("KEY명")
                .and()
                //403예외권한이 없는 경우 이동하도록
                .exceptionHandling()
                .accessDeniedPage("/eeee")
                .and()
//                .rememberMe().key("rem").tokenValiditySeconds(86400)
                .csrf().disable(); //토큰시간
    }


}
