package com.study.springboot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType; 

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {  

	@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  
		http.csrf((csrf) -> csrf.disable())
		.cors((cors) -> cors.disable())
    	.authorizeHttpRequests(request -> request  
    		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
    		.requestMatchers("/").permitAll()
            .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
            .requestMatchers("/guest/**").permitAll()  
            .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN") 
            .anyRequest().authenticated()	
        );
		
    http.formLogin(login -> login
		.loginPage("/loginForm") 			// default : /login
	    .loginProcessingUrl("/j_spring_security_check")	// 바꾸면 안됨.
        .failureUrl("/loginError") 			// default : /login?error
        .usernameParameter("j_username")	// default : j_username
        .passwordParameter("j_password") 	// default : j_password
	        .permitAll()
    );
    
    http.logout(logout ->logout
        .logoutUrl("/logout") 
	    .logoutSuccessUrl("/")
	    .permitAll()
    );
	return http.build();  
}
 
	@Bean
    protected UserDetailsService users() 
    {
        UserDetails user = User.builder()
        		.username("user")
        		.password(passwordEncoder().encode("1234"))
        		.roles("USER")	// ROLE_USER 에서 ROLE_ 자동으로 붙는다.
        		.build();
        UserDetails admin = User.builder()
        		.username("admin")
        		.password(passwordEncoder().encode("1234"))
        		.roles("USER", "ADMIN")	
        		.build();
        
        // 메모리에 사용자 정보를 담는다.
        return new InMemoryUserDetailsManager(user, admin);
    }
    
    // passwordEncoder() 추가   //버전업 되면서 아래와 같이 수정됨.
    public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
