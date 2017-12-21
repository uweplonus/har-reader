package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

/**
 * <p>
 * This model class is the container for a complete HAR JSON object.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Har {

    /** The log attribute of the HAR container. */
    @Expose
    private Log log;

    /** The default constructor. */
    public Har() { }

    /**
     * <p>
     * Returns the log attribute of the HAR.
     * </p>
     *
     * @return the log attribute.
     */
    public Log getLog() {
        return log;
    }

    /**
     * <p>
     * Sets the log attribute of the HAR.
     * </p>
     *
     * @param log the log attribute.
     */
    public void setLog(final Log log) {
        this.log = log;
    }

}
