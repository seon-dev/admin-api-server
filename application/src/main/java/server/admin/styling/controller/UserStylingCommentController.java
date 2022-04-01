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
import server.admin.utils.page.PageResult;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user-styling-comment")
public class UserStylingCommentController {
    private final UserStylingCommentService userStylingCommentService;

    @GetMapping("/user-styling/{userStylingId}")
    @ApiOperation(value = "해당 유저 스타일링의 댓글 전체 조회", notes = "해당 유저 스타일링의 모든 댓글을 조회힙니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<PageResult<UserStylingCommentResponse>> getUserStylingCommentByStylingId(
            @PathVariable("userStylingId") Long id
    ){
        return RestSuccessResponse.newInstance(
                userStylingCommentService.getUserStylingCommentByUserStyling(id)
        );
    }

    @GetMapping("/{userStylingCommentId}")
    @ApiOperation(value = "유저 스타일링 댓글 단일 조회", notes = "해당 유저 스타일링 댓글을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    public RestSuccessResponse<UserStylingCommentResponse> getUserStylingComment(
        @PathVariable("userStylingCommentId") Long id
    ){
        return RestSuccessResponse.newInstance(
                userStylingCommentService.getUserStylingComment(id)
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
