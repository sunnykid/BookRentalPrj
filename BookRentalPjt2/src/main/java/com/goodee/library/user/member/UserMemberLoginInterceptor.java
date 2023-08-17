package com.goodee.library.user.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserMemberLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response
			, Object handler) throws Exception{
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			Object obj = session.getAttribute("loginedUserMemberVo");
			if(obj != null)
				return true;
		}
		
		response.sendRedirect(request.getContextPath()+"/user/member/loginForm");
		return false;
	}
}
