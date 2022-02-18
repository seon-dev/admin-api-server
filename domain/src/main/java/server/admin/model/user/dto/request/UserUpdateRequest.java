package server.admin.model.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.user.entity.User;
import server.admin.model.user.role.UserRole;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
public class UserUpdateRequest {
    private String phoneNumber;
    private List<Long> badges;
    private UserRole role;

    public User setEntityExceptBadge(User user){
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        return user;
    }

}
