package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

public class Har {

    @Expose
    private Log log;

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
