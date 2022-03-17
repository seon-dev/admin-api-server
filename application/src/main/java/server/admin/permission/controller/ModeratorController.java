package server.admin.permission.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.permission.dto.response.ModeratorResponse;
import server.admin.service.permission.ModeratorService;
import server.admin.utils.page.PageResult;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/moderator")
public class ModeratorController {
    private final ModeratorService moderatorService;

    @GetMapping()
    @ApiOperation(value = "모더레이터의 권한 전체 조회", notes = "모더레이터에게 부여된 전체 권한을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<ModeratorResponse>> getAllModeratorPermission(

    ){
        return RestSuccessResponse.newInstance(
                moderatorService.getAllModeratorPermission()
        );
    }

    @GetMapping("/{moderatorId}")
    @ApiOperation(value = "모더레이터의 권한 단일 조회", notes = "해당 모더레이터에게 부여된 권한을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<ModeratorResponse> getModeratorPermission(
        @PathVariable("moderatorId") Long id
    ){
        return RestSuccessResponse.newInstance(
                moderatorService.getModeratorPermission(id)
        );
    }

    @DeleteMapping("/{moderatorId}")
    @ApiOperation(value = "모더레이터의 권한 삭제", notes = "해당 모더레이터에게 부여된 모든 권한을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteModeratorPermission(
        @PathVariable("moderatorId") Long id
    ){
        moderatorService.deleteModeratorPermission(id);
    }
}
