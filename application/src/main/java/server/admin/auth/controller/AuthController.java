package server.admin.auth.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.admin.model.auth.dto.request.SignInRequest;
import server.admin.model.auth.dto.request.VerifyRequest;
import server.admin.model.auth.dto.response.RefreshTokenResponse;
import server.admin.model.auth.dto.response.SignInResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.user.entity.User;
import server.admin.service.auth.AuthService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "핸드폰 번호 인증", notes = "핸드폰 번호를 인증합니다.")
    @ApiResponse(code = 200, message = "핸드폰 인증에 성공했습니다.")
    public RestResponse verify(
            @RequestBody @Valid VerifyRequest request
    ) {
        return authService.verifyingPhoneNumber(request.getPhoneNumber());
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "로그인", notes = "로그인합니다.")
    public RestResponse<SignInResponse> signIn(
            @RequestBody @Valid SignInRequest request
    ){
        return RestSuccessResponse.newInstance(
                authService.signIn(request.getPhoneNumber(), request.getVerificationCode())
        );
    }

    @PostMapping("/refresh-token")
    @ApiOperation(value = "엑세스 토큰 재발급", notes = "엑세스 토큰을 재발급합니다.")
    public RestResponse<RefreshTokenResponse> regenerateAccessToken(
            @ApiIgnore @AuthenticationPrincipal User user
    ){
        return RestSuccessResponse.newInstance(
                authService.regenerateToken(user)
        );
    }

    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "로그아웃합니다.")
    public RestResponse<String> logout(
           @ApiIgnore @AuthenticationPrincipal User user
    ){

        return RestSuccessResponse.newInstance(
                authService.logout(user)
        );
    }


}
