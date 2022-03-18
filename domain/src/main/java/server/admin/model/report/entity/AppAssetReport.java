package server.admin.model.report.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "app_asset_report")
public class AppAssetReport extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String brand;
    @Column(name = "line_or_name")
    private String lineOrName;
    @Column
    private String resource;
    @Column
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @Column(name = "is_enabled")
    private Boolean isEnabled;

}
