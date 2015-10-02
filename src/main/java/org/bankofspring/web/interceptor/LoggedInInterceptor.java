package org.bankofspring.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankofspring.web.controller.ChangeUserController;
import org.bankofspring.web.controller.DecoratorController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggedInInterceptor extends HandlerInterceptorAdapter {

	public static final String USER_SESSION_ATTRIBUTE_KEY = "bos_current_user";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession s = request.getSession();
		if (s.getAttribute(USER_SESSION_ATTRIBUTE_KEY) == null && !(handler instanceof ChangeUserController) && !(handler instanceof DecoratorController)) {
			response.sendRedirect(request.getContextPath() + "/selectUser");
			return false;
		}
		return true;
	}
	
}
