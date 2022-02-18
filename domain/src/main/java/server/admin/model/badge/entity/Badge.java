package server.admin.model.badge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.badge.dto.request.BadgeCreateUpdateRequest;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name = "badge")
public class Badge extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(length = 200)
    private String resource;
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    public static Badge toEntity(BadgeCreateUpdateRequest dto){
        final Badge entity = new Badge();
        entity.setResource(dto.getResource());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

}
