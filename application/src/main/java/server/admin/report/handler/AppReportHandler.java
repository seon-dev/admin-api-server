package server.admin.report.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.report.exception.AppReportException;

@RestControllerAdvice
public class AppReportHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AppReportErrorCode{
        APP_REPORT_NOT_EXIST_CODE(404, "해당하는 신고내역이 존재하지 않습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(AppReportException.AppReportNotExistException.class)
    public RestFailResponse AppReportNotExistException(AppReportException.AppReportNotExistException exception){
        return new RestFailResponse<>(AppReportErrorCode.APP_REPORT_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }
}
