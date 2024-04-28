package portalgen.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import portalgen.userservice.model.enums.PlaceType;
import portalgen.userservice.model.request.PlacePreferenceRequest;
import portalgen.userservice.model.response.PlacePreferenceResponse;
import portalgen.userservice.model.response.Response;
import portalgen.userservice.service.PlacePreferenceService;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/place-preference")
@RequiredArgsConstructor
public class PlacePreferenceController {
    private final PlacePreferenceService placePreferenceService;


    @PostMapping("/{userProfileId}/default")
    public Response<PlacePreferenceResponse> setDefaultPlacePreference(@PathVariable Long userProfileId) {
        return new Response<>(placePreferenceService.setDefaultPlacePreference(userProfileId));
    }

    @GetMapping("/{userProfileId}")
    public Response<PlacePreferenceResponse> getPlacePreference(@PathVariable Long userProfileId) {
        return new Response<>(placePreferenceService.getPlacePreference(userProfileId));
    }

    @PutMapping
    public Response<PlacePreferenceResponse> updatePlacePreference(@RequestBody PlacePreferenceRequest request) {
        return new Response<>(placePreferenceService.updatePlacePreference(request));
    }


    @GetMapping("/{userProfileId}/scores")
    public Response<Map<PlaceType, Float>> calculateUserPlacePreferenceScores(@PathVariable Long userProfileId) {
        return new Response<>(placePreferenceService.calculateUserPlacePreferenceScores(userProfileId));
    }
}
