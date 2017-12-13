package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

public class Log {

    @Expose
    private String version;

    @Expose
    private CreatorBrowser creator;

    @Expose
    private CreatorBrowser browser;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public CreatorBrowser getCreator() {
        return creator;
    }

    public void setCreator(CreatorBrowser creator) {
        this.creator = creator;
    }

    public CreatorBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(CreatorBrowser browser) {
        this.browser = browser;
    }

}
