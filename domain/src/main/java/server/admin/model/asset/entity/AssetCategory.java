package server.admin.model.asset.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "asset_category")
@DynamicUpdate
@DynamicInsert
public class AssetCategory extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 200)
    private String resource;

    @Column
    private Boolean isEnabled;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
//    @JsonIgnore
//    private List<AssetBrandCategory> assetBrandCategories;
}
