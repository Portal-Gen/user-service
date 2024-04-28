package portalgen.userservice.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum SocialMedialPlatform {
    FACEBOOK(0, "Facebook"),
    INSTAGRAM(1, "Instagram"),
    TIKTOK(2, "TikTok"),
    TWITTER(3, "Twitter"),
    YOUTUBE(4, "YouTube");
    private final Integer code;
    private final String value;
    private static Map<Integer, SocialMedialPlatform> mapping;
    static {
        initMapping();
    }
    SocialMedialPlatform(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
    private static void initMapping() {
        mapping = new HashMap<>();
        for (SocialMedialPlatform value : values()) {
            mapping.put(value.getCode(), value);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
