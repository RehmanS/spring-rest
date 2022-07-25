package com.spring.rest.springrest.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("rehman")
                .password("12345")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin1")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // /allEmployeesə hamının accessi var. /seach-ə yalnız adminin
        // Bu web security adlanır

        http.authorizeRequests()
                .antMatchers("/allEmployees")
                .permitAll()
                .antMatchers("/search")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().logout();
    }

    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
