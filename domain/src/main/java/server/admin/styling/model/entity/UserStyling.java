package server.admin.styling.model.entity;

import server.admin.common.BaseTimeEntity;
import server.admin.user.model.entity.User;

import javax.persistence.*;
import java.io.Serializable;

public class UserStyling extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private String text;
    @Column
    private int likes;
    @Column
    private int comments;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Column(name= "is_archived")
    private Boolean isArchived;

}
