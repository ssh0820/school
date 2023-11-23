package com.zerobase.school.config;

import com.zerobase.school.security.OAuth2.OAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //시큐리티 활성화 -> 기본 스프링 필터 체인에 등록
public class SecurityConfig {

    @Autowired
    private OAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").authenticated()
            .antMatchers("/**").hasAuthority("CUSTOMER")
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            .anyRequest().permitAll()

            .and()
            .formLogin()
            .loginPage("/signin/customer")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")

            .and()
            .oauth2Login()
            .loginPage("/signin/customer")
            .defaultSuccessUrl("/")
            .userInfoEndpoint()
            .userService(oAuth2UserService);


        return http.build();
    }
}

