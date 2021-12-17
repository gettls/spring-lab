package hello.crud_ex1.web.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.web.SessionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver{

	// @Login && Member.class resolver
	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		boolean LoginAnnotation = parameter.hasParameterAnnotation(Login.class);
		boolean MemberClass = Member.class.isAssignableFrom(parameter.getParameterType());
		
		return LoginAnnotation && MemberClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession(false);
		if(session==null) {
			log.info("no session");
			return null;
		}
		log.info("exists session");
		return session.getAttribute(SessionConst.LOGIN_MEMBER);
	}

}
