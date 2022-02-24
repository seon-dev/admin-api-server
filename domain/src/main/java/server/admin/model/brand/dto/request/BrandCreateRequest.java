package server.admin.model.brand.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BrandCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String originalName;
    @NotNull
    private MultipartFile resource;
    private MultipartFile resourceWallpaper;
    private MultipartFile resourceCard;
    @NotBlank
    private String description;
    private String color;
    @NotNull
    private Boolean isEnabled;


    public static Brand toEntity(final BrandCreateRequest request){
        final Brand entity = new Brand();
        entity.setColor(request.getColor());
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
