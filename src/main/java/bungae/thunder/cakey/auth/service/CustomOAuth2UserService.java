package bungae.thunder.cakey.auth.service;

import bungae.thunder.cakey.auth.common.OAuthAttributes;
import bungae.thunder.cakey.user.domain.SessionUser;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserService userService;
    private final HttpSession httpSession;

    public CustomOAuth2UserService(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // DefaultOAuth2User 서비스를 통해 User 정보를 가져와야 하기 때문에 대리자 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = service.loadUser(userRequest);
        // 네이버 로그인인지 카카오 로그인인지 구분해주는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행시 키가 되는 필드값 프라이머리키와 같은 값 네이버 카카오 지원 x
        String userNameAttributeName =
                userRequest
                        .getClientRegistration()
                        .getProviderDetails()
                        .getUserInfoEndpoint()
                        .getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 데이터를 담을 클래스
        OAuthAttributes attributes =
                OAuthAttributes.of(
                        registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 로그인한 유저 정보
        User user = saveOrCreate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        // 로그인한 유저를 리턴함
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("user")),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // User 저장, 이미 있는 데이터면 그대로 반환
    private User saveOrCreate(OAuthAttributes attributes) {
        User user = userService.getUserByEmail(attributes.getEmail());
        if (user == null) {
            User newUser =
                    User.builder().email(attributes.getEmail()).name(attributes.getName()).build();
            return userService.createUser(newUser);
        }

        return user;
    }
}
