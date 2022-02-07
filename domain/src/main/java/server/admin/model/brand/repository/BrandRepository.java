package server.admin.model.brand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Page<Brand> findAllByOrderByIdAsc(Pageable pageable);
    Page<Brand> findByIdGreaterThanEqualOrderByIdAsc(Long id, Pageable pageable);
    Boolean existsByIdGreaterThan(Long id);
}
