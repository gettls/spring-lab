package com.jwt.security1.config.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.jwt.security1.config.auth.PrincipalDetails;
import com.jwt.security1.config.oauth.provider.FacebookUserInfo;
import com.jwt.security1.config.oauth.provider.GoogleUserInfo;
import com.jwt.security1.config.oauth.provider.NaverUserInfo;
import com.jwt.security1.config.oauth.provider.OAuth2Userinfo;
import com.jwt.security1.model.User;
import com.jwt.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired private UserRepository userRepository;
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리하는 함수
	// userRequest는 code를 받아서 accessToken를 응답 받은 객체
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId로 어떤 Oauth로 로그인 했는지 확인 가능
		System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창-> 로그인 완료 -> code를 리턴(OAuth-Client라이브러리가 받음) -> AccessToken 요청
		// userRequest정보 -> loadUser 함수 호출 -> 구글로부터 회원 프로필 받아줌
		System.out.println(userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri());
		System.out.println(userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName());
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("getAttributes : "+ oAuth2User.getAttributes());
		
		// 회원가입을 강제로 진행해볼 예정
		OAuth2Userinfo oAuth2Userinfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2Userinfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
			System.out.println("페이스북 로그인 요청");
			oAuth2Userinfo = new FacebookUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
			System.out.println("네이버 로그인 요청");
			oAuth2Userinfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}
		String provider = oAuth2Userinfo.getProvider();
		System.out.println(provider);
		String providerId = oAuth2Userinfo.getProviderId(); // google Id
		String username = provider+"_"+providerId; // google_100120128127
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String email = oAuth2Userinfo.getEmail();
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			userEntity = User.builder()
						.username(username)
						.password(password)
						.email(email)
						.role(role)
						.provider(provider)
						.providerId(providerId)
						.build();
			userRepository.save(userEntity);
		}
		
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}
	
	
	
}
