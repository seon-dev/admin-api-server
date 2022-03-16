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
import server.admin.model.permission.repository.PermissionRepository;
import server.admin.model.user.entity.User;
import server.admin.model.user.exception.UserException;
import server.admin.model.user.repository.UserRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;


    public PageResult<PermissionResponse> getAllPermissions(){
        List<Permission> permissionList = permissionRepository.findAll();
        List<PermissionResponse> permissionResponseList = new ArrayList<>();
        permissionList.forEach(permission -> {
            permissionResponseList.add(PermissionResponse.toResponse(permission));
        });
        PageImpl<PermissionResponse> pageResult = new PageImpl<>(permissionResponseList, Pageable.unpaged(), permissionResponseList.size());
        return new PageResult<>(pageResult);
    }

    public PermissionResponse getPermission(Long id){
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        return PermissionResponse.toResponse(optionalPermission.orElseThrow(PermissionException.PermissionNotExistException::new));
    }

    public PermissionResponse createPermission(PermissionCreateRequest request){
        Permission permission = PermissionCreateRequest.toEntityExceptUser(request);
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserException.UserNotExistException::new);
        permission.setUser(user);

        return PermissionResponse.toResponse(permissionRepository.save(permission));
    }

    public void deletePermission(Long id){
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        permissionRepository.delete(optionalPermission.orElseThrow(PermissionException.PermissionNotExistException::new));
    }
}
