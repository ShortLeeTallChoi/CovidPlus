package com.covidplus.configuration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.covidplus.model.UserVO;
import com.covidplus.service.LoginService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	public LoginService loginSvc;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String member_id = authentication.getName();
		String member_pass = (String) authentication.getCredentials();
		
		UserVO userVO = loginSvc.authenticate(member_id, member_pass);
		if(userVO == null)
			throw new BadCredentialsException("Login Fail");
		
		if(!passwordEncoder.matches(member_pass, userVO.getMember_pass())) {
			throw new BadCredentialsException("Login Fail");
		}
		userVO.setMember_pass(null);
		
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userVO.getAuth_id()));
		
		return new UsernamePasswordAuthenticationToken(userVO, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
