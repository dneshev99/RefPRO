package com.refpro.server.security;

import com.refpro.server.DBhandlers.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  public WebSecurityConfig() {
    super();
  }

  @Autowired
  private UserHandler userHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests().antMatchers(HttpMethod.POST,"/user/register").permitAll();
   // http.cors().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
    http.csrf().disable().cors().and().authorizeRequests().antMatchers(HttpMethod.POST, "/login/").permitAll().and()
        .addFilterBefore(new JWTLoginFilter("/login", this.authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


    http.authorizeRequests().antMatchers("/**").authenticated();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userHandler);
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
  }

  @Autowired
  public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
    builder.authenticationProvider(this.authProvider());
  }


//
//  @Bean
//  public WebMvcConfigurer corsConfigurer() {
//      WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurerAdapter() {};
//      CorsRegistry corsReg = new CorsRegistry();
//      corsReg.addMapping("/**").allowedOrigins("http://localhost:4200").allowCredentials(true).allowedMethods("POST","PUT","GET","OPTIONS");
//      webMvcConfigurer.addCorsMappings(corsReg);
//    return webMvcConfigurer;
//  }
//
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
        corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        source.registerCorsConfiguration("/**",corsConfig );
        return source;
    }

}
