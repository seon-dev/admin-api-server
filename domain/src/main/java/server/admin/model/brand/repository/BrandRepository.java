package server.admin.model.brand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import server.admin.model.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, BrandRepositoryCustom {
    Page<Brand> findAllByIsEnabledEqualsOrderByIdAsc(Boolean isEnabled, Pageable pageable);
    Page<Brand> findByIdGreaterThanEqualAndIsEnabledEqualsOrderByIdAsc(Long id, Boolean isEnabled, Pageable pageable);
    Boolean existsByIdGreaterThan(Long id);

    @Nullable
    Brand findBrandById(Long id);
}
