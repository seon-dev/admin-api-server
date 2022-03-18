package server.admin.model.report.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name = "app_user_report")
public class AppUserReport extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String content;
    @Column
    private String resource;
    @Column
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @Column(name = "is_enabled")
    private Boolean isEnabled;

}
