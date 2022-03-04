package server.admin.model.user.dto.request;

import lombok.Getter;
import server.admin.model.user.entity.User;
import server.admin.model.user.role.UserRole;

import java.util.List;

@Getter
public class UserUpdateRequest {
    private String phoneNumber;
    private List<Long> badges;
    private UserRole role;

    public static User setEntityExceptBadge(User user, UserUpdateRequest request){
        user.setPhoneNumber(request.phoneNumber);
        user.setRole(request.role);
        return user;
    }

}
