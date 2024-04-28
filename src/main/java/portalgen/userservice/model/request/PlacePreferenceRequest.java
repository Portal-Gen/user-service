package portalgen.userservice.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import portalgen.userservice.model.enums.PlaceType;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PlacePreferenceRequest {
    private Long userProfileId;
    private Map<PlaceType, Float> preferences;
}
