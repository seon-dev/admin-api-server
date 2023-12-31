package server.admin.model.bookmark.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name = "bookmark")
public class Bookmark extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "target_type")
    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    public enum TargetType{
        ASSET_PROTOTYPE,
        STYLING
    }
}
