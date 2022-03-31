package server.admin.report.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.report.exception.AppReportCategoryException;

@RestControllerAdvice
public class AppReportCategoryHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AppReportCategoryErrorCode{
        APP_REPORT_CATEGORY_NOT_EXIST_CODE(404, "해당하는 신고 카테고리가 존재하지 않습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AppReportCategoryException.AppReportCategoryNotExistException.class)
    public RestFailResponse AppReportCategoryNotExistException(AppReportCategoryException.AppReportCategoryNotExistException exception){
        return new RestFailResponse<>(AppReportCategoryErrorCode.APP_REPORT_CATEGORY_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
