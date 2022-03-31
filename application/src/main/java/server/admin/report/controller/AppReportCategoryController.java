package server.admin.report.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.report.dto.response.AppReportCategoryResponse;
import server.admin.model.report.entity.AppReportCategory;
import server.admin.model.report.repository.AppReportCategoryRepository;
import server.admin.service.report.AppReportCategoryService;
import server.admin.service.report.AppReportService;
import server.admin.utils.page.PageResult;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/app-report-category")
public class AppReportCategoryController {
    private final AppReportCategoryService appReportCategoryService;


    @GetMapping
    @ApiOperation(value = "신고 카테고리 전체 조회", notes = "앱 신고 카테고리 전체 항목을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<AppReportCategoryResponse>> getAllAppReportCategory(){
        return RestSuccessResponse.newInstance(
                appReportCategoryService.getAllAppReportCategory()
        );
    }

    @GetMapping("/{appReportCategoryId}")
    @ApiOperation(value = "신고 카테고리 조회", notes = "해당하는 앱 신고 카테고리 항목을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppReportCategoryResponse> getAppReportCategory(
            @PathVariable("appReportCategoryId") Long id
    ){
        return RestSuccessResponse.newInstance(
                appReportCategoryService.getAppReportCategory(id)
        );
    }

}
