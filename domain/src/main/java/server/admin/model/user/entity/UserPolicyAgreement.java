package server.admin.model.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_policy_agreement")
@DynamicInsert
@DynamicUpdate
public class UserPolicyAgreement extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "personal_info_agreement", nullable = false)
    private Boolean personalInfoAgreement = false;

    @Column(name = "advertisement_marketing_agreement", nullable = false)
    private Boolean advertisementMarketingAgreement = false;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    @JsonIgnore
    private User user;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
}
