package server.admin.service.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.permission.dto.request.PermissionCreateRequest;
import server.admin.model.permission.dto.response.PermissionResponse;
import server.admin.model.permission.entity.Permission;
import server.admin.model.permission.exception.PermissionException;
import server.admin.model.permission.repository.ModeratorPermissionRepository;
import server.admin.model.permission.repository.PermissionRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            permissionResponseList.add(permissionResponse);
        });
        PageImpl<PermissionResponse> pageResult = new PageImpl<>(permissionResponseList, Pageable.unpaged(), permissionResponseList.size());
        return new PageResult<>(pageResult);
    }

    public PermissionResponse getPermission(Long id){
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        PermissionResponse permissionResponse = PermissionResponse.toResponseExceptUser(optionalPermission.orElseThrow(PermissionException.PermissionNotExistException::new));
        permissionResponse.setUserId(moderatorPermissionRepository.findByPermissionFetchJoin(optionalPermission.get()).getModerator().getId());
        return permissionResponse;
    }

    public PermissionResponse createPermission(PermissionCreateRequest request){
        return null;
    }

    public void deletePermission(Long id){

    }
}
