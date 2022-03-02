package server.admin.mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.admin.model.asset.repository.userAssetApplication.UserAssetApplicationRepository;
import server.admin.service.asset.UserAssetApplicationService;

@ExtendWith(MockitoExtension.class)
public class UserAssetApplicationServiceBase extends BasicServiceBase{

    @Mock
    private UserAssetApplicationRepository userAssetApplicationRepository;

    @InjectMocks
    private UserAssetApplicationService userAssetApplicationService;





}
