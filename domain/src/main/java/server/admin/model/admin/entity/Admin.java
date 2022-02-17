//package server.admin.model.admin.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import server.admin.model.common.BaseTimeEntity;
//import server.admin.model.admin.role.AdminRole;
//
//import javax.persistence.*;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "admin")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Admin extends BaseTimeEntity implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(length = 20)
//    private String name;
//
////    @Column(length = 30, nullable = false, unique = true)
////    private String nickname;
//
//    @Column(length = 80, nullable = false, unique = true) //rfc3696 에 따르면 이메일계정의 id는 최대 64글자
//    private String email;
//
//    @Column(length = 64
////            nullable = false
//    )
//    @Size(min = 8, max = 64) //NIST guidline에 따르면 8자이상 64자이하
//    private String password;
//
//    @Column(name = "is_enabled")
//    private Boolean isEnabled;
//
//    @Column(name = "refresh_token")
//    private String refreshToken;
//
//    @Column
//    private AdminRole role;
//    //AdminRole == enum타입{ ADMIN, MODERATOR }
//
//    @Column(name = "email_verification_code")
//    private Integer emailVerificationCode;
//
//    @Column(name = "is_verified", nullable = false)
//    private Boolean isVerified;
//
//    @Column(name = "email_verified_expired_at")
//    private Timestamp emailVerifiedExpiredAt;//+600000->ok
//
//    @Column(name = "email_verifier_valid_at")
//    private Timestamp emailVerifierValidAt;
//    //현재시간+300000->ok
//}