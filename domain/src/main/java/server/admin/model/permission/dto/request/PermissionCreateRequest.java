package server.admin.model.permission.dto.request;

import server.admin.model.permission.entity.Permission;

public class PermissionCreateRequest {

    private Long userId;
    private String section;
    private Integer level;

//    public static Permission toEntityExceptUser(PermissionCreateRequest request){
//        Permission permission = new Permission();
//
//    }
}
