package server.admin.model.user.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class UserUpdateRequest {
    private String nickname;
    private Date birthday;
    private String phoneNumber;
    private String introduce;
    private String instagram;
    private List<Integer> badges;
    private String resourceUplaoded;
    private String resourceExtension;

}
