package server.admin.model.permission.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.permission.entity.ModeratorPermission;
import server.admin.model.permission.entity.Permission;

import java.util.List;

@Getter
@Builder
public class ModeratorPermissionResponse {
    private Long id;
    private Long userId;
    private String nickname;
    private String phoneNumber;
    private List<Permission> permissions;

    public static ModeratorPermissionResponse toResponseExceptUserAndPermissions(ModeratorPermission entity){
        return ModeratorPermissionResponse.builder()
                .id(entity.getId())
                .nickname(entity.getModerator().getNickname())
                .phoneNumber(entity.getModerator().getPhoneNumber())
                .build();//user랑, 퍼미션 응답
    }
}
