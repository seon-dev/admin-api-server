package server.admin.model.brand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.entity.Brand;

import java.util.List;

public interface BrandRepositoryCustom {
    List<Brand> getAllBrand(Pageable pageable);
}
