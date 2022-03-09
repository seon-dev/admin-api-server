package server.admin.styling.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.styling.dto.response.UserStylingResponse;
import server.admin.service.styling.UserStylingService;
import server.admin.utils.page.PageResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user-styling")
public class UserStylingController {
    private final UserStylingService userStylingService;

    @GetMapping()
    @ApiOperation(value = "전체 유저 스타일링 조회", notes = "전체 유저 스타일링을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<UserStylingResponse>> getAllUserStyling(

    ){
        return RestSuccessResponse.newInstance(
                userStylingService.getAllUserStyling()
        );
    }

    @GetMapping("/{userStylingId}")
    @ApiOperation(value = "단일 유저 스타일링 조회", notes = "해당 유저 스타일링 항목을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<UserStylingResponse> getUserStyling(
        @PathVariable("userStylingId") Long userStylingId
    ){
        return RestSuccessResponse.newInstance(
                userStylingService.getUserStyling(userStylingId)
        );
    }

    @DeleteMapping("/{userStylingId}")
    @ApiOperation(value = "유저 스타일링 삭제", notes = "유저 스타일링을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserStyling(
            @PathVariable("userStylingId") Long userStylingId
    ){
        userStylingService.deleteUserStyling(userStylingId);
    }


}
