package org.yvasilchuk.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class StatusResponse implements Serializable {
    private static final long serialVersionUID = 7026515617267448901L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    public StatusResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
