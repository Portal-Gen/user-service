package portalgen.userservice.model.request;

import lombok.Data;

@Data
public class UpdateUserProfileRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private String bio;
    private String location;
}