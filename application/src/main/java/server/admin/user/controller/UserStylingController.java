package server.admin.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import server.admin.model.common.rest.RestResponse;
import server.admin.utils.page.PageResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user-styling")
public class UserStylingController {

//    @GetMapping("/{userStylingId}")
//    @ApiOperation()
//    @ResponseStatus(HttpStatus.OK)
//    public RestResponse<PageResult<UserStylingResponse>> getAllUserStyling(){
//
//    }

}
