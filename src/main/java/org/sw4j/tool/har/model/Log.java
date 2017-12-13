package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

public class Log {

    @Expose
    private String version;

    @Expose
    private Creator creator;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
