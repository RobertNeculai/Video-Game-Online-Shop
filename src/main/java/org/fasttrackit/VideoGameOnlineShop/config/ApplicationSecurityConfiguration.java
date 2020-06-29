package org.fasttrackit.VideoGameOnlineShop.config;

import org.fasttrackit.VideoGameOnlineShop.config.Security.AuthorityType;
import org.fasttrackit.VideoGameOnlineShop.service.UserPrincipalDetailsService;
import org.fasttrackit.VideoGameOnlineShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserPrincipalDetailsService userService;

    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder, UserPrincipalDetailsService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/UserRegister").permitAll()
                .antMatchers("/CustomerRegister").permitAll()
                .antMatchers("/frontPage").permitAll()
                .antMatchers(
                        "static/css/**",
                        "static/css/font-awesome.min.css",
                        "static/fonts/**",
                        "static/images/**",
                        "static/js/**").permitAll()
                .antMatchers(HttpMethod.GET,"/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/products").hasAuthority(AuthorityType.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/products").hasAuthority(AuthorityType.ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/products").hasAuthority(AuthorityType.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/user").permitAll()
                .antMatchers(HttpMethod.POST,"/user/username").permitAll()
                .antMatchers(HttpMethod.POST,"/customers").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .failureForwardUrl("/login")
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .rememberMe()
                .userDetailsService(userService)
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .key("uniqueAndSecret")
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remmeber-me")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(this.userService);
        return provider;
    }
}
