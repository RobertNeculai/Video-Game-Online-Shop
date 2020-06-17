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
                .antMatchers(HttpMethod.DELETE,"/products").hasAuthority(AuthorityType.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/products").hasAuthority(AuthorityType.ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/products").hasAuthority(AuthorityType.ADMIN.name())
                .antMatchers("/*").permitAll()

                .antMatchers(HttpMethod.GET, "/newsfeed/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/newsfeed/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/newsfeed/**").authenticated()
                .antMatchers(HttpMethod.GET, "/timeline/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/timeline/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/timeline/**").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()
                .defaultSuccessUrl("http://localhost:63342/online-shop-web-app/LogedInPage.html")
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
                .logoutSuccessUrl("/login");
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
