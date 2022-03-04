package server.admin.user.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.admin.model.auth.dto.request.SignUpRequest;
import server.admin.model.user.dto.request.UserUpdateRequest;
import server.admin.model.user.entity.User;
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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "관리자 생성", notes = "관리자를 생성합니다.")
    public RestResponse createUser(
            @RequestBody SignUpRequest request
    ){
        return userService.createUser(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 조회",notes = "오프셋 페이지네이션에 맞게, 유저 미리보기 정보를 조회합니다. 정렬 기준 디폴트:id,desc  사이즈 디폴트:25 페이지 디폴트:첫번째(0) enabled 디폴트:전체조회")
    public RestResponse<PageResult<UserProfileResponse.Minified>> getAllUser(
            @PageableDefault(size = 25, page = 0, sort = "id") final Pageable pageable,
            @RequestParam(value = "enabled", required = false) Boolean isEnabled
    ){
        return RestSuccessResponse.newInstance(
                userService.getAllUser(pageable, isEnabled)
        );

    }

//    @GetMapping("/search")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "유저 검색",notes = "오프셋 페이지네이션에 맞는 유저 미리보기 정보를 닉네임으로 검색합니다.")
//    public RestResponse<PageResult<UserProfileResponse.Minified>> searchUser(
////            @PageableDefault(size = 25, page = 0, sort = "id") final Pageable pageable,
//            @RequestParam(value = "enabled", required = false) Boolean isEnabled,
//            @RequestParam("nickname") String nickname
//    ){
//        return RestSuccessResponse.newInstance(
//                userService.searchUser(Pageable.unpaged(), nickname, isEnabled)
//        );
//
//    }

//여기부터 api 테스트해보기
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 프로필 조회",notes = "해당 유저 프로필을 조회합니다.")
    public RestResponse<UserProfileResponse> getUser(
            @PathVariable("userId") Long userId
    ){
        return RestSuccessResponse.newInstance(
                userService.getUser(userId)
        );
    }

//user profile update,delete authentication = admin
    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 프로필 업데이트", notes = "해당 유저 프로필을 업데이트합니다.")
    public RestResponse<UserProfileResponse> updateUser(
            @RequestBody UserUpdateRequest request,
            @PathVariable("userId") Long userId
    ){
        return RestSuccessResponse.newInstance(
                userService.updateUser(userId, request)
        );
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 삭제", notes = "해당 유저 프로필을 삭제합니다.")
    public RestResponse<String> deleteUser(
            @PathVariable("userId") Long userId
    ){

        return RestSuccessResponse.newInstance(
                userService.deleteUser(userId)
        );
    }


}
