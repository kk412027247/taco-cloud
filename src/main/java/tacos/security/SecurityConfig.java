package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                .password("{noop}infinity")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("{noop}bulleyes")
//                .authorities("ROLE_USER");
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities where username=?")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
//
//        auth.ldapAuthentication()
//                .userSearchBase("on=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("on=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode")
//                .contextSource()
//                .root("dc=tacocloud.dc=com");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/")
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
    }

}
