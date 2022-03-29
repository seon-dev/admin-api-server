package server.admin.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.report.dto.request.AppReportUpdateRequest;
import server.admin.model.report.dto.response.AppReportResponse;
import server.admin.service.report.AppReportService;
import server.admin.utils.page.PageResult;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/app-report")
public class AppReportController {
    private final AppReportService appReportService;

    @GetMapping
    @ApiOperation(value = "전체 신고 내역 조회", notes = "전체 신고 내역을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AppReportResponse>> getAllAppReport(

    ){
        return RestSuccessResponse.newInstance(
                appReportService.getAllAppReport()
        );
    }

    @GetMapping("/{appReportId}")
    @ApiOperation(value = "신고 내역 조회", notes = "해당 신고 내역을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppReportResponse> getAppReport(
            @PathVariable("appReportId") Long id
    ){
        return RestSuccessResponse.newInstance(
                appReportService.getAppReport(id)
        );
    }

    @PatchMapping("/{appReportId}")
    @ApiOperation(value = "신고 내역 업데이트", notes = "해당 신고 내역을 업데이트합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppReportResponse> updateAppReport(
            @PathVariable("appReportId") Long id,
            @RequestBody AppReportUpdateRequest request
    ){
        return RestSuccessResponse.newInstance(
                appReportService.updateAppReport(id, request)
        );
    }

}
