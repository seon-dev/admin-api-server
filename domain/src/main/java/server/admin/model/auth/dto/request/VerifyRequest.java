package server.admin.model.auth.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class VerifyRequest {
    @NotBlank
    private String phoneNumber;
}
