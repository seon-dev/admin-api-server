package server.admin.healthcheck.controller;

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
    public RestResponse<Any> getHealth(){
        return RestSuccessResponse.empty();
    }
}
