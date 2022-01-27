package server.admin.asset.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.admin.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "asset_collection")
public class AssetCollection extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private Boolean isEnabled;
    @Column
    private String resource;

}
