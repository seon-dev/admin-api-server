package server.admin.model.report.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name = "app_report")
public class AppReport extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(length = 20, name = "report_code")
    private String code;
    @Column(name = "reporter_id")
    private Long reporterId;
    @Column(name = "reportee_id")
    private Long reporteeId;
    @Column
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Column(name = "category_id")
    private Long categoryId;//report-type-id
    @Column(name = "verifier_id")
    private Long verifierId;
    @Column(length = 200, name = "verifier_comment")
    private String verifierComment; //1차검수자의 반려,승인에 대한 코멘트, (주로 반려에 대한 코멘트)
    @Column(name = "target_type")
    @Enumerated(EnumType.STRING)
    private ReportTargetType targetType;//COMMENT USER STYLING
    @Column(name = "target_id")
    private Long targetId;//신고대상ID
    @Column(name = "verified_at")
    private Timestamp verifiedAt;
    @Transient
    @JsonIgnore
    private User verifier;
    @Transient
    @JsonIgnore
    private User reportee;
    @Transient
    @JsonIgnore
    private User reporter;
    @Transient
    @JsonIgnore
    private AppReportCategory category;

    public enum ReportTargetType {
        STYLING, USER, COMMENT
    }

    public enum ReportStatus {
        PENDING, ACCEPTED, REJECTED, PRE_PROCESSING
    }
}
