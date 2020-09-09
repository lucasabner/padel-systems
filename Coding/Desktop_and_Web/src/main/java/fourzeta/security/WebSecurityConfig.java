package fourzeta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private ImplementsUserDetailsService userDetailsService;
//	
//	@Override
//	protected void configure(HttpSecurity httsp) throws Exception{
//		http.csrf().disable().authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/").permitAll()
//		.antMatchers(HttpMethOod.GET, "/cadastrarEvento").hasRole("ADMIN")
//		.antMatchers(HttpMethod.POST, "/cadastrarEvento").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and().formLogin().permitAll()
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.userDetailsService(userDetailsService)
//		.passwordEncoder(new BCryptPasswordEncoder());
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception{
//		web.ignoring().antMatchers("/materialize/**", "/style/**");
//	}
//}
