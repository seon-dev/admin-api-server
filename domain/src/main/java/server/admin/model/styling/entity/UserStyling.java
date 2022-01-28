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
@DynamicInsert
@DynamicUpdate
@Table(name = "user_styling")
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
