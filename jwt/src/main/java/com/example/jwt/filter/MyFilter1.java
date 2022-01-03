package com.example.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 1");
		
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		
//		// 토큰: id, pw 정상적으로 들어와서 로그인 되면 토큰을 만들어주고 응답함
//		// 요청할 때마다 header의 Authorization의 토큰을 검증한다
//		if(req.getMethod().equals("POST")) {
//			System.out.println("POST 요청");
//			String headerAuth = req.getHeader("Authorization");
//			System.out.println(headerAuth);
//			if(headerAuth.equals("cos")) {
//				chain.doFilter(request, response);
//			}else {
//				PrintWriter outPrintWriter = res.getWriter();
//				outPrintWriter.println("인증 안됨");
//			}
//		}
		chain.doFilter(request, response);
	}
	
}
