package co.edu.uniquindio.proyecto.seguridad;

import co.edu.uniquindio.proyecto.seguridad.config.JwtAuthenticationEntryPoint;
import co.edu.uniquindio.proyecto.seguridad.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        //Se debe documentar
        //http.authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated();
        //http.authorizeHttpRequests().requestMatchers("/api/usuario/cambiarContrasena/**").permitAll();

        http.authorizeHttpRequests().requestMatchers("/api/moderador/**").hasAuthority("MODERADOR");
        http.authorizeHttpRequests().requestMatchers(
                "/api/auth/**",
                "/api/producto/obtenerProducto/**",
                "/api/producto/listar/productos/**",
                "/api/producto/listar/categoria/**",
                "/api/producto/listar/buscarProcudo/**",
                "/api/categorias/**",
                "/api/imagenes/**",
                "/api/usuario/obtener/**",
                "/api/usuario/actualizar/**"
        ).permitAll().anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}