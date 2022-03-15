package server.admin.service.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.permission.dto.request.PermissionCreateRequest;
import server.admin.model.permission.dto.response.PermissionResponse;
import server.admin.model.permission.entity.Permission;
import server.admin.model.permission.repository.ModeratorPermissionRepository;
import server.admin.model.permission.repository.PermissionRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final ModeratorPermissionRepository moderatorPermissionRepository;


    public PageResult<PermissionResponse> getAllPermissions(){
        List<Permission> permissionList = permissionRepository.findAll();
        List<PermissionResponse> permissionResponseList = new ArrayList<>();
        permissionList.forEach(permission -> {
            PermissionResponse permissionResponse = PermissionResponse.toResponseExceptUser(permission);
            permissionResponse.setUserId(moderatorPermissionRepository.findByPermissionFetchJoin(permission).getModerator().getId());

        });

        return null;
    }

    public PermissionResponse getPermission(){
        return null;
    }

    public PermissionResponse createPermission(PermissionCreateRequest request){
        return null;
    }

    public void deletePermission(Long id){

    }
}
