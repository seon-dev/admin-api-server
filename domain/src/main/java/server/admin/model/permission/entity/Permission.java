package server.admin.model.permission.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "permission")
@DynamicUpdate
@DynamicInsert
public class Permission extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(length = 20)
    private String section;
    @Column
    private Integer level;
    @Column
    private Boolean isEnabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
