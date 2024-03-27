package com.asoostale.config;

import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {


    //1.
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/register").permitAll() //
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }
    /**
     * 1. InMemoryUserDetailsMange 타입의 클래스를 반환 받을 것이다.
     * 2. User(클래스)의 withDefaultPasswordEncoder를 호출한다. 이 함수의 반환값은 UserDetails(인터페이스)이다.
     * 3. 부모가 인터페이스이니 자식으로 User가 오는 것은 괜찮다.
     * 4. .username, .password, .authorities, .build함수를 호출해서 완성해준다.
     * 5. return을 new InMemoryUserDetailsManager로 받고, 매개변수는 만들어 놓은 인스턴스인 admin과 user를 넣어준다(여러 개 가능)
     */
   /* @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12311")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1111")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
    */
    //    @Bean
    //    public InMemoryUserDetailsManager detailsServiceV2() {
    //        UserDetails admin = User.withUsername("admin")
    //                .password("123111")
    //                .authorities("admin")
    //                .build();
    //
    //        UserDetails user = User.withUsername("user")
    //                .password("12311")
    //                .authorities("user")
    //                .build();
    //
    //        return new InMemoryUserDetailsManager(admin, user);
    //    }
    //
    //

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
        public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        }
}

