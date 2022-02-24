package server.admin.model.brand.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.common.BaseTimeEntity;
import server.admin.model.brand.dto.request.BrandCreateRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "brand")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @Column
    @NotBlank
    private String name;
    @Column(name = "original_name")
    @NotBlank
    private String originalName;
    @Column
    @NotBlank
    private String description;
    @Column
    private String recommendation;
    @Column
    private int likes;
    @Column
    private String resource;
    @Column(name = "resource_wallpaper")
    private String resourceWallpaper;
    @Column(name = "resource_card")
    private String resourceCard;
    @Column
    private String color;
    @Column(name = "is_enabled")
    @NotNull
    private Boolean isEnabled = true;

}
