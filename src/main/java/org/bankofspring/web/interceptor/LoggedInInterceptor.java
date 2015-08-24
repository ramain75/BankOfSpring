package org.bankofspring.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bankofspring.web.controller.ChangeUserController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggedInInterceptor extends HandlerInterceptorAdapter {

	public static final String USER_SESSION_ATTRIBUTE_KEY = "bos_current_user";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession s = request.getSession();
		if (s.getAttribute(USER_SESSION_ATTRIBUTE_KEY) == null && !(handler instanceof ChangeUserController)) {
			response.sendRedirect(createRedirectUrl(request, "/selectUser"));
			return false;
		}
		return true;
	}
	
	/**
	 * This could be in a ytil class, I've left it here for laziness
	 * @param request the {@link HttpServletRequest}
	 * @param redirect the url to create. A leading <code>/</code> indicates that the url should be context relative. Otherwise it should be relative.
	 * @return The url
	 */
	private String createRedirectUrl(HttpServletRequest request, String redirect) {
		if (redirect.startsWith("/")) {
			return request.getContextPath() + redirect;
		} else {
			return redirect;
		}
	}
}
