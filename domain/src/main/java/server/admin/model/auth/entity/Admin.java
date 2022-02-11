package server.admin.model.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.auth.role.AdminRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(length = 80, nullable = false, unique = true) //rfc3696 에 따르면 이메일계정의 id는 최대 64글자
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column
    private AdminRole role;
    //AdminRole == enum타입
}
