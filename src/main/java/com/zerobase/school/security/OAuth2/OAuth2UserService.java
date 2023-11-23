package com.zerobase.school.security.OAuth2;

import com.zerobase.school.user.domain.UserType;
import com.zerobase.school.user.domain.entity.Customer;
import com.zerobase.school.user.exception.CustomException;
import com.zerobase.school.user.exception.ErrorCode;
import com.zerobase.school.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //"registraionId" 로 어떤 OAuth 로 로그인 했는지 확인 가능(google,naver등)
        System.out.println("getClientRegistration: "+userRequest.getClientRegistration());
        System.out.println("getAccessToken: "+userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: "+ super.loadUser(userRequest).getAttributes());
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Clien라이브러리가 받아줌) -> code를 통해서 AcssToken요청(access토큰 받음)
        // => "userRequest"가 감고 있는 정보
        //회원 프로필을 받아야하는데 여기서 사용되는것이 "loadUser" 함수이다 -> 구글 로 부터 회원 프로필을 받을수 있다.


        /**
         *  OAuth 로그인 회원 가입
         */
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo =null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }
        else{
            System.out.println("지원하지 않은 로그인 서비스 입니다.");
        }

        String provider = oAuth2UserInfo.getProvider(); //google , naver, facebook etc
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password =  ""; //중요하지 않음 그냥 패스워드 암호화 하
        String email = oAuth2UserInfo.getEmail();
        UserType role = UserType.CUSTOMER;

        Customer customer = customerRepository
                .findByName(username).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
        //처음 서비스를 이용한 회원일 경우
        if(customer == null) {
            LocalDateTime createTime = LocalDateTime.now();
            customer = Customer.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .userType(role)
                    .provider(provider)
                    .provideId(providerId)
                    .verifyExpiredAt(createTime)
                    .build();

            customerRepository.save(customer);
        }

        return new OAuth2Details(customer, oAuth2User.getAttributes());
    }
}
