package server.admin.model.report.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "app_report_type")
public class AppReportCategory extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column(name = "parent_report_category_id")
    private Long parentReportCategoryId;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Transient
    @JsonIgnore
    private AppReportCategory parentReportCategory;
}