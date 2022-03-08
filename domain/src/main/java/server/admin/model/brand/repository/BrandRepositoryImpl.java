package server.admin.model.brand.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.entity.Brand;
import server.admin.model.brand.entity.QBrand;
import server.admin.model.common.QueryDslSupport;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

import static server.admin.model.brand.entity.QBrand.*;
import static server.admin.model.user.entity.QUser.user;

public class BrandRepositoryImpl extends QueryDslSupport implements BrandRepositoryCustom {

    @Autowired
    public BrandRepositoryImpl(EntityManager entityManager){
        super(Brand.class, entityManager);
    }

    @Override
    public List<Brand> getAllBrand(Pageable pageable){
        JPAQuery<?> query = queryFactory.from(brand);
//                .where(
//                        checkEnabled(isEnabled)
//                );

        final Long count = queryFactory.select(brand.count())
                .from(brand)
//                .where(
//                        checkEnabled(isEnabled)
//                )
                .fetchOne();

        List<Brand> brandList = Objects.requireNonNull(getQuerydsl())
                .applyPagination(pageable, query)
                .select(brand)
                .fetch();

        return brandList;

    }

    private BooleanExpression checkEnabled(Boolean isEnabled){
        return isEnabled != null ? user.isEnabled.eq(isEnabled) : null;
    }

    private List<Long> countQuery(Boolean isEnabled){
        List<Long> fetch = queryFactory.select(brand.count())
                .from(brand)
                .where(
                        checkEnabled(isEnabled)
                )
                .fetch();

        return fetch;
    }

}
