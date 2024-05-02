package portalgen.userservice.model.request;

import lombok.Data;

@Data
public class UpdateUserProfileRequest extends UserProfileRequest {
    private Long id;
}