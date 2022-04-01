package server.admin.service.permission;

import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.permission.dto.response.ModeratorResponse;
import server.admin.model.permission.dto.response.PermissionResponse;
import server.admin.model.permission.entity.Permission;
import server.admin.model.permission.repository.PermissionRepository;
import server.admin.model.user.entity.User;
import server.admin.model.user.exception.AdminException;
import server.admin.model.user.repository.UserRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeratorService {
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public ModeratorResponse getModeratorPermission(Long moderatorId){
        Optional<User> optionalUser = userRepository.findById(moderatorId);
        List<Permission> permissionList = permissionRepository.findByUser(optionalUser.orElseThrow(AdminException.AdminNotExistException::new));

        List<PermissionResponse> permissionResponseList = new ArrayList<>();
        permissionList.forEach(permission -> {
            permissionResponseList.add(PermissionResponse.toResponse(permission));
        });

        ModeratorResponse moderatorResponse = ModeratorResponse.toResponseExceptPermissions(optionalUser.get());
        moderatorResponse.setPermissions(permissionResponseList);

        return moderatorResponse;
    }
    @Transactional(readOnly = true)
    public PageResult<ModeratorResponse> getAllModeratorPermission(){
        List<Permission> permissionList = permissionRepository.findAllFetchJoin();
        List<ModeratorResponse> moderatorResponses = new ArrayList<>();

        permissionList.forEach(permission -> {
            ModeratorResponse moderatorResponse = ModeratorResponse.toResponseExceptPermissions(permission.getUser());
            List<PermissionResponse> permissionResponseList = new ArrayList<>();

            List<Permission> moderatorPermissionList = permissionRepository.findByUser(permission.getUser());
            moderatorPermissionList.forEach(moderatorPermission -> {
                permissionResponseList.add(PermissionResponse.toResponse(moderatorPermission));
            });

            moderatorResponse.setPermissions(permissionResponseList);
            moderatorResponses.add(moderatorResponse);

        });
        PageImpl<Permission> pageResult = new PageImpl<>(permissionList, Pageable.unpaged(), permissionList.size());
        return new PageResult(pageResult);
    }

    public void deleteModeratorPermission(Long moderatorId){
        User user = userRepository.findById(moderatorId).orElseThrow(AdminException.AdminNotExistException::new);
        List<Permission> permissionList = permissionRepository.findByUser(user);
        permissionList.forEach(permissionRepository::delete);
    }
}
