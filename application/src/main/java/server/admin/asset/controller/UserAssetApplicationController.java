package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.UserAssetApplicationCreateRequest;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.common.cursor.CursorResult;
import server.admin.service.asset.UserAssetApplicationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/user-asset-application")
@RequiredArgsConstructor
public class UserAssetApplicationController {
    private final UserAssetApplicationService userAssetApplicationService;
    private final String DEFAULT_SIZE = "5";

    @GetMapping("/{userAssetApplicationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 에셋 신청 정보 조회", notes = "단일 유저 에셋 신청 정보를 조회합니다.")
    public UserAssetApplicationResponse getUserAssetApplication(
            @PathVariable("userAssetApplicationId") Long userAssetApplicationId
    ){
        return userAssetApplicationService.getUserAssetApplication(userAssetApplicationId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 에셋 신청 정보 조회", notes = "커서 페이지네이션에 맞는 유저 에셋 신청 정보를 조회합니다.")
    public CursorResult<UserAssetApplicationResponse> getUserAssetApplication(
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam("cursorId") Long cursorId
    ){
        return userAssetApplicationService.getAllUserAssetApplication(cursorId, PageRequest.of(0, size));
    }
}
