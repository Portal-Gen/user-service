package portalgen.userservice.exception;

import jakarta.servlet.http.HttpServletResponse;
import portalgen.userservice.model.enums.PlaceType;

import java.util.Arrays;

public enum BadRequestError implements ResponseError {
    UNKNOWN(0, "Unknown error"),
    BAD_REQUEST_ERROR(100, "Bad request error"),
    USER_PROFILE_NOT_FOUND(101, "User profile not found"),
    //TODO: handle invalid place type error
    USER_ID_CANNOT_BE_BLANK(102, "User id cannot be blank"),
    USER_EMAIL_CANNOT_BE_BLANK(103, "User email cannot be blank"),
    USER_EMAIL_ALREADY_EXISTS(104, "User email already exists"),
    USER_FIRST_NAME_CANNOT_BE_BLANK(105, "User first name cannot be blank"),
    USER_LAST_NAME_CANNOT_BE_BLANK(106, "User last name cannot be blank"),
    USER_EMAIL_INVALID(107, "User email is invalid"),
    USER_EMAIL_CANNOT_BE_UPDATED(108, "User email cannot be updated"),
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