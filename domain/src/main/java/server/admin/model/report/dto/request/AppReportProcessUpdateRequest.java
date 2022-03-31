package server.admin.model.report.dto.request;

import lombok.Getter;

@Getter
public class AppReportProcessUpdateRequest {
    private Boolean verified;
    private Long verifierId;
    private String verifierComment;
    private String punishment;

}
