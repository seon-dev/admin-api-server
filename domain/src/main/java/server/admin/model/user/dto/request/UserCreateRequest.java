package server.admin.model.user.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Getter
public class UserCreateRequest {
    @NotNull
    private String nickname;
    private Date birthday;
    @NotNull
    private String phoneNumber;
    private String introduce;
    private String instagram;
    private List<Integer> badges;
    private String resourceUploaded;
    private String resourceExtension;
//    @NotNull
//    private Boolean isEnabled;
}
