package server.admin.model.brand.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class BrandCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String originalName;
    @NotNull
    private String resourceUploaded;
    @NotNull
    private String resourceExtension;
    @NotNull
    private String resourceWallpaperUploaded;
    @NotNull
    private String resourceWallpaperExtension;
    @NotNull
    private String resourceCardUploaded;
    @NotNull
    private String resourceCardExtension;
    @NotBlank
    private String description;

//    private String color;
    @NotNull
    private Boolean isEnabled;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName(String postfix) {
        return postfix == null ? uid + resourceExtension : uid + "_" + postfix + resourceExtension ;
    }


    public static Brand toEntity(final BrandCreateRequest request){
        final Brand entity = new Brand();
//        entity.setColor(request.getColor());
        entity.setDescription(request.getDescription());
        entity.setIsEnabled(true);
        entity.setName(request.getName());
//        entity.setResourceCard(request.getResourceCard());
//        entity.setResource(request.getResource());
//        entity.setResourceWallpaper(request.getResourceWallpaper());
        entity.setOriginalName(request.getOriginalName());
        return entity;
    }
}
