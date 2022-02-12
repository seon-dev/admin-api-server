package server.admin.mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.admin.model.asset.entity.Asset;
import server.admin.model.asset.entity.AssetPrototype;
import server.admin.model.asset.repository.UserAssetApplicationRepository;
import server.admin.model.user.entity.User;
import server.admin.service.asset.UserAssetApplicationService;

@ExtendWith(MockitoExtension.class)
public class UserAssetApplicationServiceBase extends BasicServiceBase{

    @Mock
    private UserAssetApplicationRepository userAssetApplicationRepository;

    @InjectMocks
    private UserAssetApplicationService userAssetApplicationService;





}
