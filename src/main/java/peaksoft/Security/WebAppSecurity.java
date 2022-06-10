package peaksoft.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import peaksoft.model.Person;

import java.net.PasswordAuthentication;

@Configuration
@EnableWebSecurity
public class WebAppSecurity {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails person = new Person(
                "ulan@gmail.com",
                passwordEncoder.encode("ulan")
        );

        return new InMemoryUserDetailsManager(user, person);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((authz) -> authz
                        .anyRequest().authenticated())
                .formLogin()
                .defaultSuccessUrl("/api/students", true)
                .permitAll();

        return http.build();
    }
        @Bean
        public PasswordEncoder createPasswordEncoder () {
            return new BCryptPasswordEncoder();
        }
    }

