package org.yvasilchuk.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Map;

public class BaseResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long serverTime = new Date().getTime();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> invalidParameters;

    public BaseResponse() {
    }

    public BaseResponse(T response) {
        this.response = response;
    }

    public BaseResponse(Throwable error) {
        this.errorMessage = error.getMessage();
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getInvalidParameters() {
        return invalidParameters;
    }

    public void setInvalidParameters(Map<String, String> invalidParameters) {
        this.invalidParameters = invalidParameters;
    }
}
