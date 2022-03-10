package server.admin.model.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.role.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;


@Entity
@Getter
@Setter
@Table(name = "`user`")
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity implements Serializable, UserDetails {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

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

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "refresh_token", length = 300)
    private String refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }


}
