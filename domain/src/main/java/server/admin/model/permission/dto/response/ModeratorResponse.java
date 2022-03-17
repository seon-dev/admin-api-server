package server.admin.model.permission.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.permission.entity.Permission;
import server.admin.model.user.entity.User;

import java.util.List;

@Getter
@Setter
@Builder
public class ModeratorResponse {
    private Long moderatorId;
    private String nickname;
    private String phoneNumber;
    private List<PermissionResponse> permissions;

    public static ModeratorResponse toResponseExceptPermissions(User entity){
        return ModeratorResponse.builder()
                .nickname(entity.getNickname())
                .phoneNumber(entity.getPhoneNumber())
                .moderatorId(entity.getId())
                .build();//user랑, 퍼미션 응답
    }
}
