package sn.seck.GestionCliniqueKissi.Service;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static sn.seck.GestionCliniqueKissi.Model.Role.ADMIN;
import static sn.seck.GestionCliniqueKissi.Model.Role.User;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .cors(Customizer.withDefaults()) /** pour activer CORS*/
                    .csrf(AbstractHttpConfigurer::disable)
                    .formLogin(form ->form
                            .loginPage("/login"))/*URL GET POUR AFFICHER LA PAGE*/
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST, "/login", "/logout",
                            "/RegisterRequest","/api/v1/auth/patients/liste").permitAll()
                        //Roles
                  //  .requestMatchers(HttpMethod.GET, "/login").permitAll()
                    .requestMatchers(HttpMethod.GET,"/admin/**").hasAnyRole("ADMIN")
                    .requestMatchers("/users/**").hasAnyRole("COMPTABLE")
                    .requestMatchers(HttpMethod.POST, "localhost:4200"
                            // SWAGGER
                            , "/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs","/v3/api-docs",
                            "/swagger-resources", "/swagger-resources/**","/v3/api-docs/**",
                            "/swagger-ui/**", "/configuration/security/**")
                        .permitAll()


                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .logout(logout ->
                            logout.logoutUrl("/api/v1/auth/logout")
                                .permitAll()
                                // .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext())
                );
                          return httpSecurity.build();

        }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * une methode pour securise le CROSS Origine request sharing(CORS)
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}