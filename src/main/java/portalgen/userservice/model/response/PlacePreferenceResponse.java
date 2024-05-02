package portalgen.userservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import portalgen.userservice.model.enums.PlaceType;

import java.util.Map;

@Data
@NoArgsConstructor
public class PlacePreferenceResponse {
    private Long userProfileId;
    private Map<PlaceType, Float> preferences;

}