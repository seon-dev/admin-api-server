package server.admin.permission.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.permission.dto.request.PermissionCreateRequest;
import server.admin.model.permission.dto.response.PermissionResponse;
import server.admin.model.permission.repository.PermissionRepository;
import server.admin.service.permission.PermissionService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    @ApiOperation(value = "권한 전체 조회",notes = "현재 존재하는 권한 전체를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<PermissionResponse>> getAllPermission(){
        return RestSuccessResponse.newInstance(
                permissionService.getAllPermissions()
        );
    }

    @GetMapping("/{permissionId}")
    @ApiOperation(value = "권한 단일 조회", notes = "해당하는 권한 항목을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PermissionResponse> getPermission(
            @PathVariable("permissionId") Long id
    ){
        return RestSuccessResponse.newInstance(
            permissionService.getPermission(id)
        );
    }

    @PostMapping()
    @ApiOperation(value = "새 권한 생성", notes = "새로운 권한과을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PermissionResponse> createPermission(
            @RequestBody @Valid PermissionCreateRequest request
            ){
        return RestSuccessResponse.newInstance(
                permissionService.createPermission(request)
        );
    }

    @DeleteMapping("/{permissionId}")
    @ApiOperation(value = "권한 삭제", notes = "권한을 삭제하고 연관된 자식 모더레이터 데이터를 함께 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deletePermission(
            @PathVariable("permissionId") Long id
    ){
        permissionService.deletePermission(id);
    }




}
