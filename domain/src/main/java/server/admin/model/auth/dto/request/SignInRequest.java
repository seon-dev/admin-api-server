package server.admin.model.auth.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class SignInRequest {
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Integer verificationCode;
}
