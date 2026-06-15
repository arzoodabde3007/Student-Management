    package com.example.springproject.studentmanagement.security.config;

    //import org.springframework.context.annotation.Bean;
    //import org.springframework.context.annotation.Configuration;
    //import org.springframework.http.HttpMethod;
    //import org.springframework.security.authentication.ProviderManager;
    //import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    //import org.springframework.security.config.Customizer;
    //import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    //import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    //import org.springframework.security.core.userdetails.UserDetailsService;
    //import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    //import org.springframework.security.crypto.password.PasswordEncoder;
    //import org.springframework.security.web.SecurityFilterChain;
    //
    //import java.util.List;
    //
    //@Configuration
    //@EnableWebSecurity
    //public class SecurityConfig {
    //
    //    private final UserDetailsService userDetailsService;
    //
    //    public SecurityConfig(UserDetailsService userDetailsService) {
    //        this.userDetailsService = userDetailsService;
    //    }
    //
    //    @Bean
    //    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //        http
    //                .csrf(csrf -> csrf.disable())
    //                .authorizeHttpRequests(auth -> auth
    //                        .requestMatchers(HttpMethod.POST, "/students").permitAll()
    //                        .anyRequest().authenticated()
    //                )
    //                .httpBasic(Customizer.withDefaults());
    //
    //        return http.build();
    //    }
    //
    //    @Bean
    //    public ProviderManager providerManager(){
    //        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
    //
    //        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    //        return new ProviderManager(List.of(daoAuthenticationProvider));
    //    }
    //
    //    @Bean
    //    public PasswordEncoder passwordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }
    //}