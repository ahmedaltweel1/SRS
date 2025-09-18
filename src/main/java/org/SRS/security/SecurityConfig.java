package org.SRS.security;


import org.SRS.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private String[] PUBLIC_ENDPOINTS={
            "/api/auth/**"
    };





@Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
@Bean
@Override
protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
}

@Bean
AuthFilter authFilter(){return new AuthFilter();
}

@Override
    protected void configure(HttpSecurity http)throws Exception{
    http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(PUBLIC_ENDPOINTS).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
}

}
