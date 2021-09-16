package com.book.springboot.config.auth;

import com.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAth2UserService customOAth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable() // h2 console화면을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests()//URL 별 권한관리 설정 옵션 시작, authorizeRequest가 선언되어야만 antMatchers 사용 가능
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()// index, css, image, js, h2 모두에게 권한 허용
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 글 CRUD 권한은 USER에게만 허용
                    .anyRequest().authenticated() //나머지 다른 요청은 인가된 사람들에게만 허용
                .and() // 로그아웃 시작
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 성공시 index
                .and()
                    .oauth2Login()//oauth 로그인 진입점
                        .userInfoEndpoint()//oauth 로그인 성공 후 사용자 정보 가져올때 설정
                            .userService(customOAth2UserService); // 소셜 로그인 성공시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
