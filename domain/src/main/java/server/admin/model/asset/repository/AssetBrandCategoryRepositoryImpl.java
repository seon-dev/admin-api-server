package server.admin.model.asset.repository;

import com.querydsl.core.types.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.common.QueryDslSupport;
import server.admin.model.user.dto.response.UserProfileResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static server.admin.model.asset.entity.QAssetBrandCategory.*;

public class AssetBrandCategoryRepositoryImpl extends QueryDslSupport implements AssetBrandCategoryRepositoryCustom {

    @Autowired
    public AssetBrandCategoryRepositoryImpl(EntityManager entityManager){
        super(AssetBrandCategory.class, entityManager);
    }

    @Override
    public List<AssetBrandCategoryResponse.Minified> findMinifiedById(Long id) {
            return queryFactory.from(assetBrandCategory)
                    .where(assetBrandCategory.id.eq(id))
                    .select(Projections.constructor(AssetBrandCategoryResponse.Minified.class,
                            assetBrandCategory.id,
                            assetBrandCategory.brand.id,
                            assetBrandCategory.category.id,
                            assetBrandCategory.category.name,
                            assetBrandCategory.isEnabled
                            ))
                    .fetch();
    }


}
