package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

/**
 * <p>
 * This is the log object of the HAR.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Log {

    /** The version of the HAR. */
    @Expose
    private String version;

    /** The creator of the HAR. */
    @Expose
    private CreatorBrowser creator;

    /** The optional browser of the HAR. */
    @Expose
    private CreatorBrowser browser;

    /** The default constructor. */
    public Log() { }

    /**
     * <p>
     * Returns the version of the HAR.
     * </p>
     *
     * @return the version of the HAR.
     */
    public String getVersion() {
        return version;
    }

    /**
     * <p>
     * Sets the version of the HAR.
     * </p>
     *
     * @param version the version of the HAR.
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * <p>
     * Returns the creator of the HAR.
     * </p>
     *
     * @return the creator of the HAR.
     */
    public CreatorBrowser getCreator() {
        return creator;
    }

    /**
     * <p>
     * Sets the creator of the HAR.
     * </p>
     *
     * @param creator the creator of the HAR.
     */
    public void setCreator(final CreatorBrowser creator) {
        this.creator = creator;
    }

    /**
     * <p>
     * Returns the browser of the HAR.
     * </p>
     *
     * @return the browser of the HAR.
     */
    public CreatorBrowser getBrowser() {
        return browser;
    }

    /**
     * <p>
     * Sets the browser of the HAR.
     * </p>
     *
     * @param browser the browser of the HAR.
     */
    public void setBrowser(final CreatorBrowser browser) {
        this.browser = browser;
    }

}
