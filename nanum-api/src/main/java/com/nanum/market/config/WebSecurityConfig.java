package com.nanum.market.config;

import com.nanum.market.config.jwt.JwtAuthorizationFilter;
import com.nanum.market.config.jwt.JwtAuthenticationFilter;
import com.nanum.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);


        http.csrf().disable();


        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                    .addFilter(corsFilter)
                    .formLogin().disable()
                    .httpBasic().disable()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))

                .authorizeRequests()
                    .antMatchers("/h2-console/**" ).permitAll()
                    .antMatchers("/user/**").permitAll()
                    .antMatchers("/boards").access("hasRole('ROLE_USER') ")
                    .antMatchers("/boards/**").permitAll()
                    .antMatchers("/kakao/**").permitAll()
                    .antMatchers("/api/chat/newChat/**").permitAll()
                    .antMatchers("/chat/send").permitAll()
                    .antMatchers("/api/chat/**")
                    .access("hasRole('ROLE_USER')")
                    .antMatchers("/personalChat/**").permitAll()
                    .anyRequest().permitAll();

        http.headers().frameOptions().disable();
    }

}
