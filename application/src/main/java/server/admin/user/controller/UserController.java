package server.admin.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.page.CustomPageResult;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.service.user.UserService;
import server.admin.utils.rest.RestResponse;
import server.admin.utils.rest.RestSuccessResponse;

import javax.crypto.spec.PSource;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 정보 조회",notes = "오프셋 페이지네이션에 맞는 유저 정보를 조회합니다.")
    public RestResponse<CustomPageResult<UserProfileResponse.Minified>> getAllUser(
            @PageableDefault(size = 25, page = 0, sort = "id") final Pageable pageable,
            @RequestParam(value = "enabled", required = false) Boolean isEnabled
    ){
        return RestSuccessResponse.newInstance(
                userService.getAllUser(pageable, isEnabled)
        );

    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 정보 검색",notes = "오프셋 페이지네이션에 맞는 유저 정보를 닉네임으로 검색합니다.")
    public RestResponse<CustomPageResult<UserProfileResponse.Minified>> searchUser(
            @PageableDefault(size = 25, page = 0, sort = "id") final Pageable pageable,
            @RequestParam(value = "enabled", required = false) Boolean isEnabled,
            @RequestParam("nickname") String nickname
    ){
        return RestSuccessResponse.newInstance(
                userService.searchUser(pageable, nickname, isEnabled)
        );

    }
}
