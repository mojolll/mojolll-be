//package io.mojolll.project.v1.api.config;
//
//import io.mojolll.project.v1.api.config.jwt.JwtAuthenticationFilter;
//import io.mojolll.project.v1.api.config.jwt.JwtAuthorizationFilter;
//import io.mojolll.project.v1.api.redis.logout.LogoutAccessTokenRedisRepository;
//import io.mojolll.project.v1.api.user.repositroy.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
//public class SecurityConfig {
//
//
//    private final UserRepository userRepository;
//    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
//    private final CorsConfig corsConfig;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable() //Authentication header에 id,pw 주는 방식 (Bearer Token 사용할거니까)
//                .apply(new MyCustomDsl()) // 커스텀 필터 등록
//                .and()
//                .authorizeRequests(authroize -> authroize
//                        .antMatchers("/**","/api/v1/users/join","/api/v1/users/login","/api/v1/users/reissue").permitAll()
//                        .antMatchers("/api/v1/users/**")
//                        .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                        .antMatchers("/api/v1/manager/**")
//                        .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                        .antMatchers("/api/v1/admin/**")
//                        .access("hasRole('ROLE_ADMIN')")
//                        .anyRequest().permitAll())
//                .build();
//    }
//
//    //AbstractHttpConfigurer가 SecurityConfigurerAdapter 상속받고 있다.
//    //AbstractHttpConfigurer<T extends AbstractHttpConfigurer<T, B>, B extends HttpSecurityBuilder<B>>
//    //HttpSecurity는 HttpSecurityBuilder 상속받고 있음
//    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
////        @Override
////        public void init(HttpSecurity builder) throws Exception {
////            super.init(builder);
////        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//            http
//                    .addFilter(corsConfig.corsFilter()) //@CrossOrigin(인증X), 시큐리티 필터에 등록 -> 인증(O)
//                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository, logoutAccessTokenRedisRepository));
//        }
//    }
//
//}