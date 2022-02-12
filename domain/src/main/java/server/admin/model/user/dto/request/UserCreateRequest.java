package server.admin.model.user.dto.request;

import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
public class UserCreateRequest {
    private String nickname;
    private Date birthday;
    private String phoneNumber;
    private String introduce;
    private String instagram;
    private List<Integer> badges;
    private String resourceUploaded;
    private String resourceExtension;
    private Boolean isEnabled;
}
