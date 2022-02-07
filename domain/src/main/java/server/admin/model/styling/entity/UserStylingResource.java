package server.admin.model.styling.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "user_styling_resource")
@DynamicInsert
@DynamicUpdate
public class UserStylingResource extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Column(length = 200)
    private String resource;

    @Column
    private short priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "styling_id")
    private UserStyling userStyling;

}
