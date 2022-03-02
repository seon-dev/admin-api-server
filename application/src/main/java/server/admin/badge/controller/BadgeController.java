package server.admin.badge.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.badge.dto.request.BadgeCreateRequest;
//import server.admin.model.badge.dto.request.BadgeCreateUpdateRequest;
import server.admin.model.badge.dto.request.BadgeUpdateRequest;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.service.badge.BadgeService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/badge")
public class BadgeController {
    private final BadgeService badgeService;
//    private final String DEFAULT_SIZE = "5";

//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("뱃지 전체 항목 조회")
//    public CursorResult<BadgeResponse> getBadges(
//            @RequestParam(value = "size", defaultValue = DEFAULT_SIZE) Integer size,
//            @RequestParam("cursorId") Long cursorId
//    ) {
//        return this.badgeService.getAllBadge(cursorId, PageRequest.of(0, size));
//    }

    @GetMapping("/{badgeId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 단일 항목 조회")
    public RestResponse<BadgeResponse> getBadge(@PathVariable("badgeId") Long badgeId){
        return RestSuccessResponse.newInstance(
                this.badgeService.getBadge(badgeId)
        );
    }

    @PatchMapping("/{badgeId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 업데이트")
    public RestResponse<BadgeResponse> updateBadge(
            @PathVariable("badgeId") Long badgeId,
            @RequestBody @Valid BadgeUpdateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                badgeService.updateBadge(badgeId,request)
        );
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 생성")
    public RestResponse<BadgeResponse> createBadge(
            @RequestBody @Valid BadgeCreateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                badgeService.saveBadge(request)
        );
    }
//
    @DeleteMapping ("/{badgeId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "뱃지 삭제", notes = "해당 뱃지 항목을 영구 삭제합니다.")
    public void deleteBadge(@PathVariable("badgeId") Long badgeId){
        badgeService.deleteBadge(badgeId);
    }

}
