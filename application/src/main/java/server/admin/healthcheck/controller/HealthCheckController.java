package server.admin.healthcheck.controller;

import io.swagger.annotations.ApiOperation;
import org.hibernate.mapping.Any;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;

@RestController
@RequestMapping("/admin/health-check")
public class HealthCheckController {
    @GetMapping
    @ApiOperation(value= "HEALTHCHECK자동배포확인테스트", notes = "API 서버의 정상 작동 여부를 확인합니다.")
    public RestResponse<Any> getHealth(){
        return RestSuccessResponse.empty();
    }
}
