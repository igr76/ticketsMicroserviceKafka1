package com.example.stmlabs;

import com.example.stmlabs.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

  private final JwtFilter jwtFilter;

  public WebSecurityConfig(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }
    @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(c->c.disable())
            .cors(c->c.disable())
            .authorizeRequests(auth -> auth
                    .requestMatchers("/users").authenticated()
                    .requestMatchers("/users/**").authenticated()
                    .requestMatchers("/ticket/allMy").authenticated()
                    .requestMatchers(HttpMethod.PUT,"/ticket").authenticated()
                    .requestMatchers(HttpMethod.DELETE,"/ticket").authenticated()
                    .anyRequest().permitAll())
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(e ->e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
    return http.build();

  }


}

