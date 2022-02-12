package server.admin.asset.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.UserAssetApplicationUpdateRequest;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.model.common.cursor.CursorResult;
import server.admin.service.asset.UserAssetApplicationService;
import server.admin.utils.rest.RestResponse;
import server.admin.utils.rest.RestSuccessResponse;

import javax.validation.constraints.NotNull;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("/admin/user-asset-application")
@RequiredArgsConstructor
public class UserAssetApplicationController {
    private final UserAssetApplicationService userAssetApplicationService;
    private final String DEFAULT_SIZE = "25";

    @GetMapping("/{userAssetApplicationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 에셋 신청 정보 조회", notes = "단일 유저 에셋 신청 정보를 조회합니다.")
    public RestResponse<UserAssetApplicationResponse> getUserAssetApplication(
            @PathVariable("userAssetApplicationId") Long userAssetApplicationId
    ){
        return RestSuccessResponse.newInstance(
                userAssetApplicationService.getUserAssetApplication(userAssetApplicationId)
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 에셋 신청 정보 조회", notes = "커서 페이지네이션에 맞는 유저 에셋 신청 정보를 조회합니다.")
    public RestResponse<CursorResult<List<UserAssetApplicationResponse>>> getAllUserAssetApplication(
            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
            @RequestParam(value = "cursor", required = false) String encodedCursor,
            @RequestParam(value = "verified", required = false) Boolean isVerified,
            @RequestParam(value = "sort", required = false, defaultValue = "id") String orderBy
    ){
        return RestSuccessResponse.newInstance(
                userAssetApplicationService.getAllUserAssetApplication(decodeCursor(encodedCursor), size, isVerified)
        );
    }

    @PatchMapping("/{userAssetApplicationId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "유저 에셋 신청 정보 업데이트", notes = "유저 에셋 신청 정보를 업데이트합니다.")
    public RestResponse<UserAssetApplicationResponse> updateUserAssetApplication(
            @PathVariable("userAssetApplicationId") Long userAssetApplicationId,
            @RequestBody @NotNull UserAssetApplicationUpdateRequest request
            ){
        return RestSuccessResponse.newInstance(
                userAssetApplicationService.updateUserAssetApplication(userAssetApplicationId, request)
        );
    }

    private Long decodeCursor(String encodedCursor){
        byte[] decodedCursor = Base64.getDecoder().decode(encodedCursor);
        Long cursorId = Long.parseLong(new String(decodedCursor));
        return cursorId;
    }
}
