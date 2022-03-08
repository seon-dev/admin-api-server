package server.admin.model.event.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "app_event")
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class AppEventEntity extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;
    @Column(length = 30)
    private String name;
    @Column(length = 30)
    private String contents;
    @Column(length = 100)
    private String resource;
    @Column(name = "resource_contents", length = 100)
    private String resourceContents;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Column(name = "is_archived")
    private Boolean isArchived;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @Column(name = "forward_to_url", length = 200)
    private String forwardToUrl;
    @Column(name = "forward_summary", length = 16)
    private String forwardSummary;
}
