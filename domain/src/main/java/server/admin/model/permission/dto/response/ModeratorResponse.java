package server.admin.model.permission.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.permission.entity.Permission;

import java.util.List;

@Getter
@Builder
public class ModeratorResponse {
    private Long id;
    private Long userId;
    private String nickname;
    private String phoneNumber;
    private List<Permission> permissions;

    public static ModeratorResponse toResponseExceptPermissions(Permission entity){
        return ModeratorResponse.builder()
                .id(entity.getId())
                .nickname(entity.getUser().getNickname())
                .phoneNumber(entity.getUser().getPhoneNumber())
                .userId(entity.getUser().getId())
                .build();//user랑, 퍼미션 응답
    }
}
