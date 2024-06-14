/*
 * package com.mpsedc;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfiguration; import
 * org.springframework.security.config.annotation.web.configurers.
 * AbstractHttpConfigurer; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter;
 * 
 * import com.mpsedc.serviceImpl.LoginServiceImpl;
 * 
 * @EnableWebSecurity
 * 
 * @Configuration public class SecurityConfigurer { // @Autowired // private
 * LoginServiceImpl loginServiceImpl;
 * 
 * @Autowired private JwtRequestFilter jwtRequestFilter; LoginServiceImpl
 * loginServiceImpl =new LoginServiceImpl();
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception { http.csrf(AbstractHttpConfigurer::disable)
 * .authorizeHttpRequests( auth ->
 * auth.requestMatchers("/authenticate").permitAll().anyRequest().authenticated(
 * )) .sessionManagement(session ->
 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
 * 
 * http.addFilterBefore(jwtRequestFilter,
 * UsernamePasswordAuthenticationFilter.class); return http.build(); }
 * 
 * @Bean public AuthenticationManager
 * authenticationManager(AuthenticationConfiguration
 * authenticationConfiguration) throws Exception { return
 * authenticationConfiguration.getAuthenticationManager(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * }
 */