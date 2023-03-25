package com.myblog.ConfigurerPackage;//85th STEP

import com.myblog.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//101 STEP(Annotation)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService userDetailsService; //97th Step


    @Bean
    PasswordEncoder passwordEncoder(){//86th Step
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {//102 STEP
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//                .csrf().disable()//85th Step
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();     (FOR ALL USER)


        http
                .csrf().disable()//87th Step
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()//.hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/auth/**").permitAll()//103 STEP
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();            //(FOR PARTICULAR USER LIKE admin, user, or something else)




    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//98th STEP
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
////In Memory Authentication
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {//88th STEP
//        UserDetails userVishal = User.builder().username("vishal")
//                .password(passwordEncoder().encode("test")).roles("USER").build();
//        UserDetails userAdmin = User.builder().username("admin")
//                .password(passwordEncoder().encode("test1")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(userVishal, userAdmin);
//    }


