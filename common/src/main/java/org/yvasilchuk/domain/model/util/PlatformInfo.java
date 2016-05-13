package org.yvasilchuk.domain.model.util;

import org.yvasilchuk.domain.messages.ValidationMessages;
import org.yvasilchuk.domain.model.enums.PlatformType;

import javax.validation.constraints.NotNull;

public class PlatformInfo {
    @NotNull(message = ValidationMessages.PLATFORM_TYPE_REQUIRED)
    private PlatformType platformType;

    private String deviceToken;

    public PlatformInfo() {
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }
}
