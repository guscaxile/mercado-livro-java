package com.mercado.java.mercadolivrojava.config;

import com.mercado.java.mercadolivrojava.repository.CustomerRepository;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerRepository customerRepository;
    private final UserDetailsCustomService userDetails;
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customEntryPoint;

    private static final String[] PUBLIC_MATCHERS = new String[]{};
    private static final String[] PUBLIC_POST_MATCHERS = new String[]{"/customers"};
    private static final String[] PUBLIC_GET_MATCHERS = new String[]{"/books"};
    private static final String[] ADMIN_MATCHERS = new String[]{"/admin/**"};


    public SecurityConfig(
            CustomerRepository customerRepository,
            UserDetailsCustomService userDetails,
            JwtUtil jwtUtil,
            CustomAuthenticationEntryPoint customEntryPoint
    ){
        this.customerRepository = customerRepository;
        this.userDetails = userDetails;
        this.jwtUtil = jwtUtil;
        this.customEntryPoint = customEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_POST_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_GET_MATCHERS).permitAll()
                .antMatchers(ADMIN_MATCHERS).hasAnyAuthority(Role.ADMIN.getDescription())
                .anyRequest().authenticated();
        http.addFilter(new AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil));
        http.addFilter(new AuthenticationFilter(authenticationManager(), userDetails, jwtUtil));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(customEntryPoint);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    @Bean
    public CorsFilter corsConfig(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
