package server.admin.model.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Getter
@Setter
@Table(name = "`user`")
@DynamicUpdate
@DynamicInsert
public class User extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    private Integer following;
    private Integer follower;

    @Column(length = 200)
    private String resource;

    private Date birthday;

    @Column(name = "phone_number", length = 20, unique = true)
    private String phoneNumber;

    @Column(name = "profile_link", length = 50)
    private String profileLink;

    @Column(length = 200)
    private String introduce;

    @Column(length = 50)
    private String instagram;

    @Column(name = "nickname_update_valid_at")
    private java.sql.Timestamp nicknameUpdateValidAt;

    @Column(name = "phone_number_verifier_valid_at")
    private java.sql.Timestamp phoneNumberVerifierValidAt;

    @Column(name = "login_verification_code")
    private Integer loginVerificationCode;

    @Column(name = "login_verification_expired_at")
    private java.sql.Timestamp loginVerificationExpiredAt;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @JsonIgnore
//    private List<UserBadgeEntity> userBadges;

}
