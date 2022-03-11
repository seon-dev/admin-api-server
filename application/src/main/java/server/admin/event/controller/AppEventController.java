package server.admin.event.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.event.dto.request.AppEventCreateRequest;
import server.admin.model.event.dto.request.AppEventUpdateRequest;
import server.admin.model.event.dto.response.AppEventResponse;
import server.admin.service.event.AppEventService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/app-event")
public class AppEventController {
    private final AppEventService appEventService;

    @GetMapping()
    @ApiOperation(value = "전체 앱 이벤트 조회" , notes = "전체 앱 이벤트를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AppEventResponse>> getAllAppEvent(

    ){
        return RestSuccessResponse.newInstance(
                appEventService.getAppEvents(Pageable.unpaged())
        );
    }

    @GetMapping("/{appEventId}")
    @ApiOperation(value = "앱 이벤트 조회", notes = "해당 앱 이벤트를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppEventResponse> getAppEvent(
            @PathVariable("appEventId") Long id
    ){
        return RestSuccessResponse.newInstance(
                appEventService.getAppEvent(id)
        );
    }

    @PostMapping
    @ApiOperation(value = "앱 이벤트 생성", notes = "앱 이벤트를 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppEventResponse> createAppEvent(
            @RequestBody @Valid AppEventCreateRequest request
    ) throws Exception {
        return RestSuccessResponse.newInstance(
                appEventService.createAppEvent(request)
        );
    }

    @PatchMapping("/{appEventId}")
    @ApiOperation(value = "앱 이벤트 업데이트", notes = "앱 이벤트를 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppEventResponse> updateAppEvent(
            @PathVariable("appEventId") Long id,
            @RequestBody @Valid AppEventUpdateRequest request
            ) throws Exception {
        return RestSuccessResponse.newInstance(
                appEventService.updateAppEvent(id, request)
        );
    }

    @DeleteMapping("/{appEventId}")
    @ApiOperation(value = "앱 이벤트 삭제", notes = "앱 이벤트를 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAppEvent(
            @PathVariable("appEventId") Long id
    ) {
        appEventService.deleteAppEvent(id);
    }


}
