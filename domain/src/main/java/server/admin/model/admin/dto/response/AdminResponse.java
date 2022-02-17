//package server.admin.model.admin.dto.response;
//
//import lombok.Builder;
//import lombok.Getter;
//import server.admin.model.admin.entity.Admin;
//
//import java.util.List;
//
//@Getter
//@Builder
//public class AdminResponse {
//    private Long id;
//    private String name;
//    private String email;
//    private List<AdminPermissionResponse> permissions;
//
//    public static AdminResponse toResponseWithoutPermissions(Admin entity){
//        return AdminResponse.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .email(entity.getEmail())
//                .build();
//    }
//}
