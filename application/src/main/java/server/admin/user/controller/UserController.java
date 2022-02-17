package server.admin.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.utils.page.PageResult;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.service.user.UserService;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 정보 조회",notes = "오프셋 페이지네이션에 맞는 유저 정보를 조회합니다.")
    public RestResponse<PageResult<UserProfileResponse.Minified>> getAllUser(
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
    public RestResponse<PageResult<UserProfileResponse.Minified>> searchUser(
            @PageableDefault(size = 25, page = 0, sort = "id") final Pageable pageable,
            @RequestParam(value = "enabled", required = false) Boolean isEnabled,
            @RequestParam("nickname") String nickname
    ){
        return RestSuccessResponse.newInstance(
                userService.searchUser(pageable, nickname, isEnabled)
        );

    }

//여기부터 api 테스트해보기
//    @GetMapping("/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "유저 정보 검색",notes = "해당 유저 정보를 닉네임으로 검색합니다.")
//    public RestResponse<UserProfileResponse> getUser(
//            @PathVariable("userId") Long userId
//    ){
//        return RestSuccessResponse.newInstance(
//                userService.getUser(userId)
//        );
//    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "유저 정보 등록",notes = "새로운 유저 정보를 등록합니다.")
//    public RestResponse<UserProfileResponse> createUser(
//        @RequestBody @NotNull UserCreateRequest request
//    ){
//        return RestSuccessResponse.newInstance(
//                userService.createUser(request)
//        );
//    }
}
