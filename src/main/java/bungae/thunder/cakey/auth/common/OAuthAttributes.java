package bungae.thunder.cakey.auth.common;

import bungae.thunder.cakey.user.domain.User;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    // 사용자 정보는 Map이기 때문에 변경해야함
    public static OAuthAttributes of(
            String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofNaver("id", attributes);
    }

    public User toEntity() {
        return User.builder().name(name).email(email).build();
    }

    private static OAuthAttributes ofNaver(
            String userNameAttributeName, Map<String, Object> attributes) {
        // 응답 받은 사용자의 정보를 Map형태로 변경.
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        // 미리 정의한 속성으로 빌드.
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
}
