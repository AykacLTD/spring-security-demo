package com.aykacltd.sec.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityAppConfig {

    private static final String[] WHITE_LIST_URL = {"/login/**"};

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbUserPassword;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req ->
                req.requestMatchers( "/").permitAll()
                       .requestMatchers("/user", "/register", "/error", "/success", "/dologin").permitAll()
                        .requestMatchers("/home/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
        )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login endpoint
                        .defaultSuccessUrl("/home" )  // Redirect after successful login
                        .permitAll()
                    )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")    // The URL to trigger logout (default is /logout)
                        .logoutSuccessUrl("/login?logout")  // URL to redirect to after logout
                        .deleteCookies("JSESSIONID")        // Optional: delete cookies
                        .invalidateHttpSession(true)        // Invalidate session
                );
               // .logout((logout) -> logout.logoutSuccessUrl("/login"));
        // @formatter:on
        return http.build();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbUserPassword);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverName);

        return dataSource;
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public InMemoryUserDetailsManager setupUsers() {

        UserDetails user1 = User.withUsername("alper")
                .password("$2a$10$nG0kW6DLk.J.zIIOAnjHQO/yb3AEH.2egxXREIbKK2kau3Aacyp2O") //password
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
