package server.admin.model.asset.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "asset_collection")
@DynamicUpdate
@DynamicInsert
public class AssetCollection extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
    @Column
    private String resource;

}
