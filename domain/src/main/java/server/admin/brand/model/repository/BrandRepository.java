package server.admin.brand.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.brand.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    public Page<Brand> findAllByOrderByIdDesc(Pageable pageable);
    public Page<Brand> findByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
    public Boolean existsByIdLessThan(Long id);
}
