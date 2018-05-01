package com.pos.lotto.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static HttpSession getHttpSession(HttpServletRequest request) {
		HttpSession session = null;
		if (null != request) {
			session = request.getSession(false);
		}
		return session;
	}

	public static HttpSession createSession(HttpServletRequest servletRequest) {
		HttpSession httpSession = null;
		if (servletRequest != null) {
			httpSession = getHttpSession(servletRequest);
			if (httpSession == null) {
				httpSession = servletRequest.getSession(true);

				// httpSession.setMaxInactiveInterval(600);
			}
		}
		return httpSession;
	}

}
