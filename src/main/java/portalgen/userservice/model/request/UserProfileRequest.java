package portalgen.userservice.model.request;

import lombok.Data;

@Data
public class UserProfileRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private String bio;
    private String location;
}
