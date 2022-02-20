package server.admin.model.brand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.admin.model.brand.dto.response.BrandResponse;

import java.util.Optional;

public interface BrandRepositoryCustom {
    Page<BrandResponse.Minified> getAllBrand(Pageable pageable, Boolean isEnabled);
}