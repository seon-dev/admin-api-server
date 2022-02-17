package server.admin.model.user.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
public class UserUpdateRequest {
    @NotNull
    private String nickname;
    private Date birthday;
    @NotNull
    private String phoneNumber;
    private String introduce;
    private String instagram;
    private List<Integer> badges;
    private String resourceUplaoded;
    private String resourceExtension;
//이거 넣어야될꺼같음, 물어보기
//    private Boolean isEnabled;
}
