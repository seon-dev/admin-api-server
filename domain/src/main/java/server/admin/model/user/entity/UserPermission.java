package server.admin.model.user.entity;

import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;

public class UserPermission {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String section;
    @Column
    private int level;
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
}
