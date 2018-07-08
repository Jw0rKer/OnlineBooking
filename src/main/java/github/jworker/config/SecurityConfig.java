package github.jworker.config;

import github.jworker.service.impl.core.authentication.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IUserService iUserService;*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(  new UserService() );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http
                .authorizeRequests().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll().and().httpBasic();
*/





        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .and().formLogin()  //login configuration
                .loginPage("/login")
              /*  .loginProcessingUrl("/user/profile")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")*/
                .and().logout()    //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and().exceptionHandling() //exception handling configuration
                .accessDeniedPage("/error");




/*

        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
*/




      /*  http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error=true")
              *//*  .successHandler(new RefererRedirectionAuthenticationSuccessHandler())
                .defaultSuccessUrl("/user/profile")*//*
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");*/
    }
/*

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

*/

}
