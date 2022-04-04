package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.report.entity.AppReport;
import server.admin.model.user.dto.response.UserProfileResponse;

import java.sql.Timestamp;
@Getter
@Setter
@Builder
public class AppReportResponse {
    private Long id;
    private String code;
    private UserProfileResponse.Minified reporter;
    private UserProfileResponse.Minified reportee;
    private AppReport.ReportTargetType targetType;
    private AppReportCategoryResponse category;
    private Long targetId;
    private AppReport.ReportStatus reportStatus;
    private Timestamp createdAt;
    private UserProfileResponse.Minified verifier;
    private String verifierComment;
    private Timestamp verifiedAt;
    private Boolean isEnabled;

    public static AppReportResponse toResponseExcept(AppReport entity){
        return AppReportResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
//                .reporterId(entity.getReporter())
//                .reporteeId(entity.getReportee()))
                .targetType(entity.getTargetType())
//                .categoryId(entity.getCategoryId())
                .targetId(entity.getTargetId())
                .reportStatus(entity.getStatus())
                .createdAt(entity.getCreatedAt())
//                .verifierId(entity.getVerifierId())
                .verifierComment(entity.getVerifierComment())
                .verifiedAt(entity.getVerifiedAt())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
