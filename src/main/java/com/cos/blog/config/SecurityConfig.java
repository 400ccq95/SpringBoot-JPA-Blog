package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

// Bean 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // IoC관리
public class SecurityConfig {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
    @Bean 
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         
	        http
	        .csrf().disable() //csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
	        .authorizeRequests()
	        	.antMatchers("/","/auth/**","/js/**","/css/**", "/images/**", "/dummy/**")
	        	.permitAll()
               .anyRequest()
               .authenticated()
           .and()
              .formLogin()
              .loginPage("/auth/loginForm")
              .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 로그인을 가로챈다.
	       .defaultSuccessUrl("/"); //정상적으로 요청이 완료       
	        return http.build();
	    }
}