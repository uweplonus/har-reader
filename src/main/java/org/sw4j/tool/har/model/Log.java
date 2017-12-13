package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

public class Log {

    @Expose
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
