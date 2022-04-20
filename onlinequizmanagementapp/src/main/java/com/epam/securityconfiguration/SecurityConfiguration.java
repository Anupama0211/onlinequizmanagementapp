package com.epam.securityconfiguration;

import com.epam.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        //auth.inMemoryAuthentication().withUser("guest").password("12345").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//          //Using Spring Default login page
//        http.csrf().disable().authorizeHttpRequests()
//                .antMatchers("/", "/register", "/login")
//                .permitAll()
//                .antMatchers("/**")
//                .hasAnyRole("ADMIN")
//                .and().formLogin();

        //Using custom login page
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/users/register","/register", "/login", "/authenticate")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().formLogin().loginPage("/login").successForwardUrl("/adminPortal");
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
