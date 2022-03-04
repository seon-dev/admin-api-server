package server.admin.model.asset.dto.response;

import lombok.*;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.brand.dto.response.BrandResponse;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetLineResponse {
    private Long id;
    private BrandResponse.Minified brand;
    private AssetBrandCategoryResponse.Minified brandCategory;
    private String name;
    private String resource;
    private Boolean isEnabled;

    public static AssetLineResponse toResponse(AssetLine entity){
        return AssetLineResponse.builder()
                .id(entity.getId())
                .brand(BrandResponse.Minified.of(entity.getBrand()))
                .brandCategory(AssetBrandCategoryResponse.Minified.of(entity.getBrandCategory()))
                .name(entity.getName())
                .resource(entity.getResource())
                .isEnabled(entity.getIsEnabled())
                .build();

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Minified{
        private Long id;
        private Long brandId;
        private Long brandCategoryId;
        private String name;
        private String resource;
        private Boolean isEnabled;

        public static Minified of(AssetLine entity){
            return Minified.builder()
                    .id(entity.getId())
                    .brandId(entity.getBrand().getId())
                    .brandCategoryId(entity.getBrandCategory().getId())
                    .name(entity.getName())
                    .resource(entity.getResource())
                    .isEnabled(entity.getIsEnabled())
                    .build();
        }

    }
}
