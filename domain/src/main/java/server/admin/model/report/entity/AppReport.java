package server.admin.model.report.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

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
    private String reportCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporterId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportee_id")
    private User reporteeId;
    @Column
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private AppReportCategory categoryId;//report-type-id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verifier_id")
    private User verifierId;
    @Column(length = 200, name = "verifier_comment")
    private String verifierComment; //1차검수자의 반려,승인에 대한 코멘트, (주로 반려에 대한 코멘트)
    @Column(name = "report_target")
    @Enumerated(EnumType.STRING)
    private ReportTarget target;//COMMENT USER STYLING
    @Column(name = "target_id")
    private Long targetId;//신고대상ID

    public enum ReportTarget {
        STYLING, USER, COMMENT
    }

    public enum ReportStatus {
        PENDING, ACCEPTED, REJECTED, DEFAULT
    }
}
