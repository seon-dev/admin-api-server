package server.admin.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Transient;

public class UserPolicyAgreement {
    @Column(name = "personal_info_agreement", nullable = false)
    private Boolean personalInfoAgreement;

    @Column(name = "advertisement_marketing_agreement", nullable = false)
    private Boolean advertisementMarketingAgreement;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private java.sql.Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
}
