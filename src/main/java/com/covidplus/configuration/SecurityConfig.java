package com.covidplus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.covidplus.service.LoginService;
import com.covidplus.service.impl.LoginServiceImpl;
import com.covidplus.util.loginFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private LoginServiceImpl loginService;
	
	@Autowired
	loginFailureHandler failureHandler;
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
             .antMatchers("/admin/**").hasRole("ADMIN")			//admin/ 이 포함된 경로는 ADMIN 권한 유저만 접근 가능
                .antMatchers("/member/**").hasRole("MEMBER")	//user/myinfo 가 포함된 경로는 MEMBER 권한 유저만 접근 가능
                .anyRequest().permitAll()					//그외 나머지 페이지는 권한과 상관없이 접근 가능
            .and() // 로그인 설정
            .formLogin()
                .loginPage("/login")							//로그인 페이지 경로
                .loginProcessingUrl("/login/loginProcess")		//로그인 진행 경로
                .defaultSuccessUrl("/index")					//로그인 성공시 이동 페이지
            //  .failureForwardUrl("/login")					//로그인 실패시 이동 페이지
                .failureHandler(failureHandler)		//로그인 실패시 로직
                .usernameParameter("member_id")					//유저 아이디로 넘겨줄 파라미터
                .passwordParameter("member_pass")				//유저 비번으로 넘겨줄 파라미터
                .permitAll()
            .and() // 로그아웃 설정
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))	//로그아웃 진행 경로
                .logoutSuccessUrl("/user/logout/result")							//로그아웃 성공시 이동 페이지
                .invalidateHttpSession(true)										//로그아웃된 세션 초기화
            .and()
                // 403 예외처리 핸들링
            .exceptionHandling().accessDeniedPage("/user/denied");
        http.headers(headers -> headers.cacheControl(cache -> cache.disable()));//post 새로고침 양식다시제출 확인 x
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	super.configure(auth);
    	auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }
}
