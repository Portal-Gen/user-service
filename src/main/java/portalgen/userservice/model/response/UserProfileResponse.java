package portalgen.userservice.model.response;

import lombok.Data;

@Data
public class UserProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private String bio;
    private String location;
}
