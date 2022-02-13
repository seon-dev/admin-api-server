package server.admin.model.auth.dto.response;

import lombok.Getter;

@Getter
public class SignInResponse {
    private String accessToken;
    private String refreshToken;

}
