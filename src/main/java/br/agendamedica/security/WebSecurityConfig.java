package br.agendamedica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private ImplementsUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        		.antMatchers(HttpMethod.GET, "/usuarios/**","/medicos/**","/pacientes/**","/planosaudes/**","/agendamentos/**","/documentacao_agendamentos/**","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html/**").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/**","/medicos/**","/pacientes/**","/planosaudes/**","/agendamentos/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/usuarios/**","/medicos/**","/pacientes/**","/planosaudes/**","/agendamentos/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/usuarios/**","/medicos/**","/pacientes/**","/planosaudes/**","/agendamentos/**").permitAll()
                .anyRequest().authenticated().and().formLogin()
                .permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}