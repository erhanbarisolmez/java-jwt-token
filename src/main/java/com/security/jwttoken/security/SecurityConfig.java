package com.security.jwttoken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.security.jwttoken.model.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  
  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService){
    this.userDetailsService = userDetailsService;
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity security, HandlerMappingIntrospector introspector) throws Exception{
    MvcRequestMatcher.Builder mvcRequestBuilder = new MvcRequestMatcher.Builder(introspector);
    
    security
      .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
      .csrf(
        csrfConfig -> csrfConfig.ignoringRequestMatchers(mvcRequestBuilder.pattern("/public/**"))
        )
      .userDetailsService(userDetailsService) 
      .authorizeHttpRequests(x -> 
        x 
          .requestMatchers("/private/user").hasRole(Role.ROLE_USER.getValue())
          .requestMatchers("/private/admin").hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_SADMIN.getValue())
          .requestMatchers("/private/sadmin").hasRole(Role.ROLE_SADMIN.getValue())
          .requestMatchers("/public/**").permitAll()
          .requestMatchers("/private/**")
                  .hasAnyRole(
                  Role.ROLE_ADMIN.getValue(),
                  Role.ROLE_SADMIN.getValue())
            .anyRequest().authenticated()
      )
          .formLogin(AbstractHttpConfigurer::disable) 
          .httpBasic(Customizer.withDefaults());

      return security.build();

  }
  
}
