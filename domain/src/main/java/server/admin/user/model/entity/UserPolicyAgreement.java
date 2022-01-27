package server.admin.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import server.admin.common.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_policy_agreement")
@DynamicInsert
@DynamicUpdate
public class UserPolicyAgreement extends BaseTimeEntity implements Serializable {
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
