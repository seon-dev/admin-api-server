package server.admin.model.auth.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class SignUpRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
