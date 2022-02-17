package server.admin.model.auth.dto.request;

import lombok.Getter;
import server.admin.model.user.entity.User;
import server.admin.model.user.role.UserRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class SignUpRequest {
    private String name;
    @NotBlank
    private String nickname;
    private Timestamp birthday;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Boolean isEnabled;
    private UserRole role;

    public User toEntity(SignUpRequest request){
        User entity = new User();
        entity.setName(this.name);
        entity.setNickname(nickname);
        entity.setBirthday(new Date(birthday.getTime()));
        entity.setPhoneNumber(phoneNumber);
        entity.setIsEnabled(isEnabled);
        entity.setRole(role);
        entity.setIsEnabled(true);
        entity.setPhoneNumberVerifierValidAt(new Timestamp(System.currentTimeMillis() + + 60000));
        return entity;
    }
}
