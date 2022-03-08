package server.admin.styling.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.model.common.rest.RestResponse;
import server.admin.model.common.rest.RestSuccessResponse;
import server.admin.model.styling.dto.response.UserStylingCommentResponse;
import server.admin.model.styling.entity.UserStylingComment;
import server.admin.service.styling.UserStylingCommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user-styling-comment")
public class UserStylingCommentController {
    private final UserStylingCommentService userStylingCommentService;

    @GetMapping("/{userStylingId}")
    @ApiOperation(value = "유저 스타일링의 댓글 조회", notes = "해당 유저 스타일링의 모든 댓글을 조회힙니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<UserStylingCommentResponse>> getUserStylingComment(
            @PathVariable("userStylingId") Long userStylingId
    ){
        return RestSuccessResponse.newInstance(
                userStylingCommentService.getUserStylingCommentByUserStyling(userStylingId)
        );
    }

    @DeleteMapping("/{userStylingCommentId}")
    @ApiOperation(value = "유저 스타일링 댓글 삭제", notes = "해당 유저 스타일링 댓글과 연관된 대댓글을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserStylingComment(
            @PathVariable("userStylingCommentId") Long userStylingCommentId
    ){
        userStylingCommentService.deleteUserStylingComment(userStylingCommentId);
    }

}
