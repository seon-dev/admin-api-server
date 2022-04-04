package server.admin.service.styling;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.styling.dto.response.UserStylingCommentResponse;
import server.admin.model.styling.entity.UserStylingComment;
import server.admin.model.styling.exception.UserStylingCommentException;
import server.admin.model.styling.repository.UserStylingCommentRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserStylingCommentService {
    private final UserStylingCommentRepository userStylingCommentRepository;

    @Transactional(readOnly = true)
    public PageResult<UserStylingCommentResponse> getUserStylingCommentByUserStyling(Long id){
        List<UserStylingComment> userStylingCommentList = userStylingCommentRepository.findAllByStylingId(id);
        List<UserStylingCommentResponse> userStylingCommentResponses = new ArrayList<>();
        userStylingCommentList.forEach(userStylingComment -> {
            userStylingCommentResponses.add(UserStylingCommentResponse.toResponse(userStylingComment));
        });
        PageImpl<UserStylingCommentResponse> pageResult = new PageImpl<>(userStylingCommentResponses, Pageable.unpaged(), userStylingCommentResponses.size());
        return new PageResult<>(pageResult);
    }

    public void deleteUserStylingComment(Long id){
        Optional<UserStylingComment> optionalUserStylingComment = userStylingCommentRepository.findById(id);
        UserStylingComment userStylingComment = optionalUserStylingComment.orElseThrow(UserStylingCommentException.UserStylingCommentNotExistException::new);
        userStylingComment.setIsEnabled(false);
        List<UserStylingComment> userStylingCommentList = userStylingCommentRepository.findByUserStylingCommentId(id);
        userStylingCommentList.forEach(singleUserStylingComment -> {
            singleUserStylingComment.setIsEnabled(false);
        });
    }

    @Transactional(readOnly = true)
    public UserStylingCommentResponse getUserStylingComment(Long userStylingCommentId){
        UserStylingComment userStylingComment = userStylingCommentRepository.findByIdFetchJoin(userStylingCommentId).orElseThrow(UserStylingCommentException.UserStylingCommentNotExistException::new);

        return UserStylingCommentResponse.toResponse(userStylingComment);
    }

}
