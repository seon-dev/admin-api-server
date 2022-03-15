package server.admin.model.permission.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.permission.entity.Permission;

@Getter
@Setter
@Builder
public class PermissionResponse {
    private Long id;
    private Long userId;
    private String section;
    private Integer level;

    public static PermissionResponse toResponse(Permission entity){
        return PermissionResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .section(entity.getSection())
                .level(entity.getLevel())
                .build();
    }
}
