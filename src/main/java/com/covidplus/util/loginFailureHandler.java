package com.covidplus.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.covidplus.dao.LoginDao;
import com.covidplus.model.UserVO;

@Component
public class loginFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
        errMessage(request);
        request.getRequestDispatcher("").forward(request, response);
	}
	
	public void errMessage(HttpServletRequest request) {
		String user_id = request.getParameter("member_id");
        String password = request.getParameter("member_pass");
        
        UserVO userVO = new UserVO();
        userVO.setMember_id(user_id);
        
        RequestContextUtils.getInputFlashMap(request);
        
        if(loginDao.memberInfoOne(userVO) != null) {
        	request.setAttribute("message", "비밀번호를 다시 확인 해주세요.");
        }else {
//        	request.setAttribute("message", "아이디를 다시 확인 해주세요.");
        }
	}
}
