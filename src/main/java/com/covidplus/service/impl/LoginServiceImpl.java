package com.covidplus.service.impl;

import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.configuration.SecurityConfig;
import com.covidplus.dao.LoginDao;
import com.covidplus.model.UserVO;
import com.covidplus.service.LoginService;
import com.covidplus.util.AlertAndRedirect;
import com.covidplus.util.Converter;

@Service
public class LoginServiceImpl implements LoginService,UserDetailsService {
	@Autowired
	public LoginDao loginDao;
	
	@Override
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@Override		//로그인 정보 조회, 이후 로그인 로직은 UserAuthenticationProvider class 확인
	public UserVO authenticate(String id, String pass) {
		return loginDao.memberInfoOne(new UserVO(id));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView join(Map<String,Object> paramMap) {
		PasswordEncoder pEncoder = SecurityConfig.passwordEncoder();
		
		Function<Map,UserVO> f1 = map ->{
			UserVO userVO = Converter.map2voConvert(map, UserVO.class);
			userVO.setMember_pass(pEncoder.encode(userVO.getMember_pass()));
			return userVO;
		};
		try {
			loginDao.memberJoin(f1.apply(paramMap));
			return AlertAndRedirect.success("회원가입에 성공했습니다", "/index");
		} catch (Exception e) {
			e.printStackTrace();
			return AlertAndRedirect.failure("회원가입중 오류가 발생했습니다.");
		}
	}

}
