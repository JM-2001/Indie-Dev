package com.csc340.IndieDev.security;

import com.csc340.IndieDev.user.CustomUserDetailsService;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import java.io.IOException;
import java.util.Set;


/**
 * This is the main security class
 * Updated the authority levels for the mappings, can be found in the securityFilerChain method
 * If a user is not signed in, they can only view the welcome, log in, and register pages
 * Apparently, you must allow the unsigned in user to be able to view the css along with the css,
 *    a major issue I had was the css not showing up for users not signed in.
 *    Solution --> .requestMatchers("/*.css").permitAll() --> permits all users to see css, no matter the
 *    authority level
 *
 */


    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig {

        private CustomUserDetailsService userDetailsService;


        /**
         * This method accelerating the debugging issues by throwing an exception when something
         * went wrong during sign in -- leaving it in for future use
         *
        private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }
        */


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
            requestCache.setMatchingRequestParameterName(null);
            http
                    .csrf(AbstractHttpConfigurer::disable)

                    .authorizeHttpRequests((authorize) -> authorize
                            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                    DispatcherType.ERROR).permitAll()


                            //AUTHORITY, not ROLE
                            //MAPPINGS ALONG WITH THEIR RESPECTIVE AUTHORITY LEVELS
                            .requestMatchers("/","/*.css", "/register", "/images/**","/portfolio/**").permitAll()
                            .requestMatchers("/home","/createProject", "/post/**", "/chat","/chats/**", "/send-message", "/profile","/id=*" , "/update","/createPosts").hasAnyAuthority("USER", "MOD", "ADMIN")
                            .requestMatchers("/changeAuthorization/**").hasAuthority("MOD")
                            .requestMatchers("/delete/**").hasAuthority("ADMIN")
                            .requestMatchers("/accountwarning").hasAnyAuthority("LOCKEDUSER")
                            .anyRequest().authenticated()
                    )
                    .formLogin((form) -> form
                            .loginPage("/login")
                            .permitAll()
                            .successHandler((request, response, authentication) -> {
                                handleAuthenticationSuccess(request, response, authentication);
                            })

                    ).exceptionHandling((x) -> x.accessDeniedPage("/403"))
                    .logout(logout -> logout
                            .logoutUrl("/logout")  // Specify the logout URL
                            .logoutSuccessUrl("/login")  // Redirect to the login page after logout
                            .permitAll()
                    )
                    .requestCache((cache) -> cache
                            .requestCache(requestCache)
                    );

            return http.build();
        }

        private void handleAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

            if (roles.contains("LOCKEDUSER")) {
                response.sendRedirect("/accountwarning");
            } else {
                response.sendRedirect("/home");
            }
        }

        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }


        //Changed from BCrypt to NoOp, Bcrypt would not allow hardcoded passwords to be entered
        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

}
