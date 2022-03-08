package server.admin.model.styling.repository;

import java.util.List;

public interface UserStylingResourceRepositoryCustom{
    List<String> findResourcesByStylingId(Long id);
}
