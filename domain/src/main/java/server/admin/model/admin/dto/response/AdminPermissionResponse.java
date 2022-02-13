package server.admin.model.admin.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.admin.entity.AdminPermission;

@Getter
@Builder
public class AdminPermissionResponse {
    private Long id;
    private Long adminId;//moderatorId
    private String section;
    private Integer level;

    public static AdminPermissionResponse toResponse(AdminPermission entity){
        return AdminPermissionResponse.builder()
                .id(entity.getId())
                .adminId(entity.getAdmin().getId())
                .section(entity.getSection())
                .level(entity.getLevel())
                .build();
    }
}