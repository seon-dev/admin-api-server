package server.admin.styling.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.common.BaseTimeEntity;
import server.admin.user.model.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@DynamicUpdate
@DynamicInsert
public class UserStylingComment extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "styling_id")
    private UserStyling styling;
    @Column
    private String text;
    @Column
    private int likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "styling_comment_id")
    private UserStylingComment userStylingComment;
    @Column
    private int comments;

}
