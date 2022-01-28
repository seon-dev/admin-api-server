package server.admin.model.like.entity;

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
@Table(name = "likes")
@DynamicInsert
@DynamicUpdate
public class Like extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "target_type")
    private TargetType targetType;

    @Column(name = "target_id")
    private Long targetId;

    public enum TargetType{
        ASSET_PROTOTYPE, STYLINH, COMMENT, BRAND
    }
}
