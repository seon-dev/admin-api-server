package server.admin.model.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@NoArgsConstructor
//@Data
public class JwtIdentityDto {
    private String userId;
    private String nickname;
    private String role;
}
