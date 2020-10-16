package com.spring.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.service.UtilService;
import com.spring.service.UtilServiceImpl;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter implements HttpSessionListener {
	@Autowired
	UtilService utilService;

	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		boolean dupl_result = false;
		if (session.getAttribute("id") != null) {
			HashMap<String, Object> member = new HashMap<>();
			member.put("user_num", session.getAttribute("user_num"));
			member.put("id", session.getAttribute("id"));

			int dupl_cnt = utilService.dupl_cnt(member);
			System.out.println("dupl_cnt : " +dupl_cnt);
			if (dupl_cnt != 1) {	
				session.invalidate();
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert(' 누군가 다중접속하여서 로그아웃되었습니다.');");
				out.println("location.href='./';");
				out.println("</script>");
			} else if (dupl_cnt == 1) {
				dupl_result = true;

			} else if (dupl_cnt == 0) {
				dupl_result = true;
			}
		} else {
			dupl_result = true;
		}
		return dupl_result;
	}

	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
				se.getSession().getServletContext(), FrameworkServlet.SERVLET_CONTEXT_PREFIX + "appServlet");
		UtilServiceImpl utilService = (UtilServiceImpl) context.getBean("utilService");

		String id = (String) se.getSession().getAttribute("id");
		String user_num = String.valueOf(se.getSession().getAttribute("user_num"));
		HashMap<String, Object> value = new HashMap<>();
		value.put("id", id);
		value.put("user_num", user_num);
		int session_db_del = utilService.session_db_del(value);

	}

}
