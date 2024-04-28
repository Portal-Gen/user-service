package portalgen.userservice.exception;

import jakarta.servlet.http.HttpServletResponse;
import portalgen.userservice.model.enums.PlaceType;

import java.util.Arrays;

public enum BadRequestError implements ResponseError {
    UNKNOWN(0, "Unknown error"),
    BAD_REQUEST_ERROR(100, "Bad request error"),
    USER_PROFILE_NOT_FOUND(101, "User profile not found"),
    //TODO: handle invalid place type error
    INVALID_PLACE_TYPE(150, "Invalid PlaceType value. Allowed values are: " + Arrays.toString(PlaceType.values())),
    ;


    private final int errorCode;
    private final String message;

    BadRequestError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public Integer getCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public int getStatus() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }
}