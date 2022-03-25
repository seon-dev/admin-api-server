package server.admin.model.report.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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
@DynamicInsert
@DynamicUpdate
@Table(name = "app_report_process")
public class AppReportProcess extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_report_id")
    private AppReport appReportId;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verifier_id")
    private User verifierId;
    @Column(length = 200, name = "verifier_comment")
    private String verifierComment;
    @CreationTimestamp
    @Column(name = "verified_at")
    private Timestamp verifiedAt;
    @Column(length = 200)
    private String punishment;
    @Column(name = "is_enabled")
    private Boolean isEnabled;

}
