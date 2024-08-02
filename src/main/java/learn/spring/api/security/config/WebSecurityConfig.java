package learn.spring.api.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        //@formatter:off
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.GET, "/products/*", "/products")
                .hasAnyRole("ADMIN","USER")
            .requestMatchers(HttpMethod.POST, "/products")
                .hasRole("ADMIN")
            .requestMatchers("/products/*")
                .hasRole("ADMIN")
            .requestMatchers("/login","/register")
                .permitAll());

        http.csrf(csrf-> csrf.ignoringRequestMatchers(
				new RegexRequestMatcher("/products", "POST"),
				new RegexRequestMatcher("/products", "PUT")
                )
            );
        //@formatter:on

        return http.build();
    }
}
