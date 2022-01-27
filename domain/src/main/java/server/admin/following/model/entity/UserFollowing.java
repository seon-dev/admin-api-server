package server.admin.following.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.common.BaseTimeEntity;
import server.admin.user.model.entity.User;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class UserFollowing extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @Column(name="is_enabled")
    private Boolean isEnabled;



}
