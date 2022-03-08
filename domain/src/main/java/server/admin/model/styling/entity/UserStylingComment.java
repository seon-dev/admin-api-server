package server.admin.model.styling.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "user_styling_comment")
@DynamicUpdate
@DynamicInsert
public class UserStylingComment extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "styling_id")
    private UserStyling styling;
    @Column
    private String text;
    @Column
    private Integer likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "styling_comment_id")
    private UserStylingComment userStylingComment;
    @Column
    private int comments;

}
