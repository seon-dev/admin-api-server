package server.admin.model.asset.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.asset.dto.response.*;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static server.admin.model.asset.entity.QAssetPrototype.*;
import static server.admin.model.user.entity.QUser.user;

public class AssetPrototypeRepositoryImpl
        extends QueryDslSupport
        implements AssetPrototypeRepositoryCustom {

    @Autowired
    public AssetPrototypeRepositoryImpl(EntityManager entityManager) {
        super(AssetPrototype.class, entityManager);
    }

    @Override
    public Optional<AssetPrototype> findByIdWithFetchJoin(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(assetPrototype)
                        .leftJoin(assetPrototype.brand, QBrand.brand).fetchJoin()
                        .leftJoin(assetPrototype.line, QAssetLine.assetLine).fetchJoin()
                        .leftJoin(assetPrototype.season, QAssetSeason.assetSeason).fetchJoin()
                        .leftJoin(assetPrototype.brandCategory, QAssetBrandCategory.assetBrandCategory).fetchJoin()
                        .where(assetPrototype.id.eq(id))
                .fetchOne());
    }

    @Override
    public Page<AssetPrototypeResponse> getAllAssetPrototype(Pageable pageable, Boolean isEnabled){
        JPAQuery<AssetPrototype> query = queryFactory.selectFrom(assetPrototype)
                .where(
                        checkEnabled(isEnabled)
                )
                .leftJoin(assetPrototype.brand, QBrand.brand)
                .leftJoin(assetPrototype.line, QAssetLine.assetLine)
                .leftJoin(assetPrototype.brandCategory, QAssetBrandCategory.assetBrandCategory)
                .leftJoin(assetPrototype.line.brand, QBrand.brand)
                .leftJoin(assetPrototype.line.brandCategory,QAssetBrandCategory.assetBrandCategory)
                .leftJoin(assetPrototype.season, QAssetSeason.assetSeason);

        List<AssetPrototype> fetch = queryFactory.selectFrom(assetPrototype)
                .where(
                        checkEnabled(isEnabled)
                )
                .leftJoin(assetPrototype.brand, QBrand.brand)
                .leftJoin(assetPrototype.line, QAssetLine.assetLine)
                .leftJoin(assetPrototype.brandCategory, QAssetBrandCategory.assetBrandCategory)
                .leftJoin(assetPrototype.line.brand, QBrand.brand)
                .leftJoin(assetPrototype.line.brandCategory,QAssetBrandCategory.assetBrandCategory)
                .leftJoin(assetPrototype.season, QAssetSeason.assetSeason)
                .fetch();

        System.out.println(fetch.size());

        Long count = queryFactory.select(assetPrototype.count())
                .from(assetPrototype)
                .where(
                        checkEnabled(isEnabled)

                )
                .fetchOne();


//       Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query)

        List<AssetPrototypeResponse> assetPrototypeResponseList = query
                .select(Projections.constructor(AssetPrototypeResponse.class,
                        assetPrototype.id,
                        projectBrandResponse(),
//                        null,
                        projectAssetLine(),
                        projectAssetSeason(),
                        projectBrandCategory(),
                        assetPrototype.name,
                        assetPrototype.decorator,
                        assetPrototype.code,
                        assetPrototype.description,
                        assetPrototype.releasePrice,
                        assetPrototype.additional,
                        assetPrototype.trendy,
                        assetPrototype.likes,
                        assetPrototype.keywords,
                        assetPrototype.resourceFront,
                        assetPrototype.resourceAdditional,
                        assetPrototype.resourceRear,
                        assetPrototype.resourceSide,
                        assetPrototype.isEnabled
                ))
                .fetch();

//        List<AssetPrototypeResponse> assetPrototypeResponseList = query.select(new QAssetPrototypeResponse(
//                assetPrototype.id,
//                projectBrandResponse(),
//                projectAssetLine(),
//                projectAssetSeason(),
//                projectBrandCategory(),
//                assetPrototype.name,
//                assetPrototype.decorator,
//                assetPrototype.code,
//                assetPrototype.description,
//                assetPrototype.releasePrice,
//                assetPrototype.additional,
//                assetPrototype.trendy,
//                assetPrototype.likes,
//                assetPrototype.keywords,
//                assetPrototype.resourceFront,
//                assetPrototype.resourceAdditional,
//                assetPrototype.resourceRear,
//                assetPrototype.resourceSide,
//                assetPrototype.isEnabled
//        )).fetch();

        System.out.println(assetPrototypeResponseList.size());
//        return assetPrototypeResponseList;

        return new PageImpl<>(assetPrototypeResponseList, pageable, count);
    }

//    private Field getFieldValue(String fieldName, String search) throws NoSuchFieldException {
//        // 1. field value
//        // 2. field value에 해당하는 매서드 => is
//        // 3. is.call(search)
//        return user.getClass().getDeclaredField(fieldName);
//    }

    private BooleanExpression checkEnabled(Boolean isEnabled){
        return isEnabled != null ? assetPrototype.isEnabled.eq(isEnabled) : null;
    }

    private ConstructorExpression<BrandResponse.Minified> projectBrandResponse(){
        ConstructorExpression<BrandResponse.Minified> constructor = Projections.constructor(BrandResponse.Minified.class,
                assetPrototype.brand.id,
                assetPrototype.brand.name,
                assetPrototype.brand.originalName,
                assetPrototype.brand.resource
        );
        System.out.println("brand "+ constructor);
        return constructor;
    }

    private ConstructorExpression<AssetLineResponse.Minified> projectAssetLine(){
        ConstructorExpression<AssetLineResponse.Minified> constructor = Projections.constructor(AssetLineResponse.Minified.class,
                assetPrototype.line.id,
                QAssetLine.assetLine.brand.id,
                QAssetLine.assetLine.brandCategory.id,
                assetPrototype.line.name,
                assetPrototype.line.resource,
                assetPrototype.line.isEnabled
        );
        System.out.println("assetLine "+ constructor);
        return constructor;
    }

    private ConstructorExpression<AssetSeasonResponse> projectAssetSeason(){
        ConstructorExpression<AssetSeasonResponse> constructor = Projections.constructor(AssetSeasonResponse.class,
                assetPrototype.season.id,
                assetPrototype.season.name,
                assetPrototype.season.resource,
                assetPrototype.season.isEnabled
        );
        System.out.println("assetSeason "+constructor);
        return constructor;
    }

    private ConstructorExpression<AssetBrandCategoryResponse.Minified> projectBrandCategory(){
        ConstructorExpression<AssetBrandCategoryResponse.Minified> constructor = Projections.constructor(AssetBrandCategoryResponse.Minified.class,
                assetPrototype.brandCategory.id,
                assetPrototype.brandCategory.brand.id,
                assetPrototype.brandCategory.category.id,
                assetPrototype.brandCategory.category.name,
                assetPrototype.brandCategory.isEnabled
        );
        System.out.println("brandCategory "+constructor);
        return constructor;
    }

//        private OrderSpecifier<Long> OrderByAsc(String orderBy){

        //정렬조건을 동적으로 가능하게 하기
//        if(Objects.equals(orderBy, "id")) return userAssetApplication.id.asc();
//        if(Objects.equals(orderBy, "asset")) return userAssetApplication.asset.id.asc();
//        ...


//        return orderBy == "" ?  userAssetApplication.id.desc() : ;
    }

