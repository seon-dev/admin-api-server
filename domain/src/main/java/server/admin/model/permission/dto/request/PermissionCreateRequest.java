package server.admin.model.permission.dto.request;

import lombok.Getter;
import server.admin.model.permission.entity.Permission;

import javax.validation.constraints.NotNull;

@Getter
public class PermissionCreateRequest {
    @NotNull
    private Long userId;
    @NotNull
    private String section;
    @NotNull
    private Integer level;

    public static Permission toEntityExceptUser(PermissionCreateRequest request){
        Permission permission = new Permission();
        permission.setSection(request.getSection());
        permission.setIsEnabled(true);
        permission.setLevel(request.getLevel());
        return permission;
    }
}
