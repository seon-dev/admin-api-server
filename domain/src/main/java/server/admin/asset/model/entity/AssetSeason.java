package server.admin.asset.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "asset_season")
@DynamicUpdate
@DynamicInsert
public class AssetSeason extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String resource;
    @Column
    private String name;
    @Column
    private Boolean isEnabled;

}
