package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.report.entity.AppReportProcess;

import java.sql.Timestamp;
@Getter
@Builder
public class AppReportProcessResponse {
    private Long id;
    private Long reportId;
    private Boolean verified;
    private Long verifierId;
    private String verifierComment;
    private Timestamp verifiedAt;
    private String punishment;
    private Boolean isEnabled;

    public static AppReportProcessResponse toResponse(AppReportProcess entity){
        return AppReportProcessResponse.builder()
                .id(entity.getId())
                .reportId(entity.getAppReportId())
                .verified(entity.getIsVerified())
                .verifierId(entity.getVerifierId())
                .verifierComment(entity.getVerifierComment())
                .verifiedAt(entity.getVerifiedAt())
                .punishment(entity.getPunishment())
                .isEnabled(entity.getIsEnabled())
                .build();
    }

}
