package server.admin.model.admin.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class AdminPermission extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User admin;
    @Column
    private String section;
    @Column
    private int level;
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
}
