package learn.spring.api.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import learn.spring.api.security.MyUserDetailsService;

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
                .permitAll()
            .requestMatchers(HttpMethod.PUT, "/product/*")
                .hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/product/*")
                .hasRole("ADMIN")
            .requestMatchers("/login","/register")
                .permitAll());
        
        // http.csrf(csrf-> csrf.ignoringRequestMatchers(
		// 		new RegexRequestMatcher("/products", "POST"),
		// 		new RegexRequestMatcher("/products", "PUT")
        //         )
        //     );
        http.csrf(config -> config.disable());
        //@formatter:on

        return http.build();
    }
}
