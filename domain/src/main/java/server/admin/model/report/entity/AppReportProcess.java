package server.admin.model.report.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "app_report_id")
    private Long appReportId;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "verifier_id")
    private Long verifierId;
    @Column(length = 200, name = "verifier_comment")
    private String verifierComment;
    @CreationTimestamp
    @Column(name = "verified_at")
    private Timestamp verifiedAt;
    @Column(length = 200)
    private String punishment;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Transient
    @JsonIgnore
    private AppReport appReport;
    @Transient
    @JsonIgnore
    private User verifier;



}
