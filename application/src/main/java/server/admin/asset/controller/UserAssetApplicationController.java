package server.admin.asset.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.asset.dto.request.UserAssetApplicationUpdateRequest;
import server.admin.model.asset.dto.response.UserAssetApplicationResponse;
import server.admin.utils.cursor.CursorResult;
import server.admin.service.asset.UserAssetApplicationService;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.utils.page.PageResult;

import javax.validation.constraints.NotNull;
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
            @RequestParam(value = "enabled", required = false) Boolean isEnabled,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc
    ){
          return RestSuccessResponse.newInstance(
                userAssetApplicationService.getAllUserAssetApplication(
                        decodeCursor(encodedCursor), size, isVerified,
                        checkOrderByAndSort(desc, sortBy),isEnabled
                )
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
                userAssetApplicationService.updateUserAssetApplication(
                        userAssetApplicationId, request
                )
        );
    }

    private Long decodeCursor(String encodedCursor){
        if(encodedCursor != null){
            byte[] decodedCursor = Base64.getDecoder().decode(encodedCursor);
            return Long.parseLong(new String(decodedCursor));
        } else return null;
    }

    private Sort checkOrderByAndSort(Boolean desc, String sortBy ){
        return desc ? Sort.by(Sort.Direction.DESC, sortBy) : Sort.by(Sort.Direction.ASC, sortBy) ;
    }
}
