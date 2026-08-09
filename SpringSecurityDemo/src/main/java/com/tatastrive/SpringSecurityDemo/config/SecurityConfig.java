package com.tatastrive.SpringSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;



//Tells Spring that this class contains configuration settings.
//Instead of writing configuration in XML, we now write it in Java.
//Marks this class as a configuration class.
//Spring automatically reads this class during application startup.

//why?

//----------Without @Configuration, Spring will ignore your security settings.

@Configuration
public class SecurityConfig {
    /*
     @Bean---It tells Spring to create and manage an object (called a Bean).
          --- Spring stores this object in the Spring IoC Container.
          ---The object can then be used anywhere in the application.

     Real-Time Example
     Think of Spring as a hotel manager.
               You request a room.
               The hotel manager gives you a room

       Springboot:
       You request a object
       Spring creates and manages it.

     */
    @Bean

	/*
	 HttpSecurity class-
	 HttpSecurity is used to define:
            Which URLs are public.
            Which URLs require login.
            Which authentication method to use.
            Other security rules.

            An object provided by Spring Security.
            It is used to configure:

            Authentication
             Authorization
            URL permissions
             Login
             Logout
              CSRF
             Basic Authentication

             http---variable name

     SecurityFilterChain--interface
     A filter chain checks every HTTP request before it reaches your controller.

     flow:

                      Browser
                          │
                          ▼
                   Security Filter
                            │
                            ▼
                          Controller



	  .csrf(csrf->csrf.disable())
	  CSRF stands for Cross-Site Request Forgery.
	  It is a security attack where a malicious website tricks a logged-in user into performing unwanted actions.

	  Why disable CSRF here?
	  For REST APIs tested using Postman, CSRF protection is usually disabled
	  during development because Postman does not use browser sessions.

	  This defines authorization rules.-->Who can access which URL?
	  requestMatchers("/")
	  http://localhost:8080/


	  permitAll()
      Allows everyone to access it.
      No login required.

	  permitAll()
      Allows everyone to access it.


      anyRequest()
      Means every request that is not matched earlier.

       .httpBasic(Customizer.withDefaults());---
	     Enables HTTP Basic Authentication.


	     Customizer.withDefaults()
        Uses Spring Security's default Basic Authentication configuration.
        No extra customization is required.


  User Opens Browser
        │
        ▼
http://localhost:8080/admin
        │
        ▼
SecurityFilterChain
        │
        ▼
Check Authentication
        │
   ┌────┴────┐
   │         │
Logged In?   No
   │         │
   ▼         ▼
Controller  Login Dialog
   │
   ▼
Response









@Configuration
Tells Spring that this class contains configuration settings.

@Bean
Creates a Spring Bean managed by the Spring container.

SecurityFilterChain
Defines the application's security rules.

permitAll()
Everyone can access this URL.


authenticated()
Login is required.


httpBasic()

Enables Basic Authentication.
Browser displays a login dialog.

	 */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/")
                        .permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }
}