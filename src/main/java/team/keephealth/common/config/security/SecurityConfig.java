package team.keephealth.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.keephealth.xyj.modules.login.mail.MailAuthenticationSecurityConfig;
import team.keephealth.common.filter.JwtAuthenticationTokenFilter;
import team.keephealth.xyj.modules.login.password.PasswordAuthenticationSecurityConfig;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String[] ANON = {
            "/login/password",
            "/login/email",
            "/register"
    };
    private String[] AUTH = {
            "/logout",
            "/email/bind",
            "/upload/**",
            "/users/**",
            "/follow/**",
            "/shows/**",
            "/profiles/**",
            "/comment/show/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Lazy
    @Autowired
    private MailAuthenticationSecurityConfig mailAuthenticationSecurityConfig;
    @Lazy
    @Autowired
    private PasswordAuthenticationSecurityConfig passwordAuthenticationSecurityConfig;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/v2/api-docs-ext/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/doc.html");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //??????csrf
                .csrf().disable()
                //???????????????????????????????????????
                .apply(mailAuthenticationSecurityConfig).and()
                //???????????????????????????????????????
                .apply(passwordAuthenticationSecurityConfig).and()
                //?????????Session??????SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                //??????????????????
                .antMatchers(ANON).anonymous()
                //??????????????????
                .antMatchers(AUTH).authenticated()

                .antMatchers("/sys/**").hasRole("admin")
                // ????????????????????????????????????????????????????????????
                .anyRequest().permitAll();

        http.logout().disable();

        //?????????????????????
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        //?????????????????????
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //????????????
        http.cors();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
