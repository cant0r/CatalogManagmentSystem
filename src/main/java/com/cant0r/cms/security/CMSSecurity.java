package com.cant0r.cms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CMSSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN").and()
                .withUser("cms").password("{noop}cms").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/hello")
                    .permitAll()
                .mvcMatchers("/api/types/all").authenticated()
                .mvcMatchers("/api/types/all-params").authenticated()
                .mvcMatchers("/api/types/**").hasRole("ADMIN")
                .mvcMatchers("/api/types").hasRole("ADMIN")

                .mvcMatchers("/api/entries/all").authenticated()
                .mvcMatchers("/api/entries/all-params").authenticated()
                .mvcMatchers("/api/entries/**").authenticated()
                .mvcMatchers("/api/entries").authenticated()

                .mvcMatchers("/api/containers/all").authenticated()
                .mvcMatchers("/api/containers/all-params").authenticated()
                .mvcMatchers("/api/containers/**").authenticated()
                .mvcMatchers("/api/containers").authenticated()

                .mvcMatchers("/api/mediums/all").authenticated()
                .mvcMatchers("/api/mediums/all-params").authenticated()
                .mvcMatchers("/api/mediums/**").authenticated()
                .mvcMatchers("/api/mediums").authenticated()

                .anyRequest().permitAll().and().csrf().disable().cors()
                .and().httpBasic();
    }
}
