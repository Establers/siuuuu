package com.siuuuu.establers.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final UserDetailsService userService;

    // JS H2 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> {
            web.ignoring()
                    .requestMatchers(toH2Console())
                    .requestMatchers("/static/**");
        };
    }

    // https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html
    // 필수 참고 Deprecated 된 API 존재
    // 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http
             .authorizeHttpRequests((auth) -> auth
                 .requestMatchers("/login", "/signup", "/user").permitAll()
                 .anyRequest().authenticated()
             )
             .formLogin((formLogin) -> formLogin
                     .loginPage("/login")
                     .defaultSuccessUrl("/articles"));

         http
            .logout((logout) ->
                    logout.logoutSuccessUrl("/login")
                            .invalidateHttpSession(true)
            );

         http.csrf((csrf) -> csrf.disable());

         return http.build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http
//            , BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
