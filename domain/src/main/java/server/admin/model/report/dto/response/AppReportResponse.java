package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.report.entity.AppReport;

import java.sql.Timestamp;
@Getter
@Builder
public class AppReportResponse {
    private Long id;
    private String code;
    private Long reporterId;
    private Long reporteeId;
    private AppReport.ReportTargetType targetType;
    private Long categoryId;
    private Long targetId;
    private AppReport.ReportStatus reportStatus;
    private Timestamp createdAt;
    private Long verifierId;
    private String verifierComment;
    private Timestamp verifiedAt;
    private Boolean isEnabled;

    public static AppReportResponse toResponse(AppReport entity){
        return AppReportResponse.builder()
                .id(entity.getId())
                .code(entity.getReportCode())
                .reporterId(entity.getReporterId())
                .reporteeId(entity.getReporteeId())
                .targetType(entity.getTargetType())
                .categoryId(entity.getCategoryId())
                .targetId(entity.getTargetId())
                .reportStatus(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .verifierId(entity.getVerifierId())
                .verifierComment(entity.getVerifierComment())
                .verifiedAt(entity.getVerifiedAt())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
