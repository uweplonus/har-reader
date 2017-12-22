package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

/**
 * <p>
 * This model class represents either a creator or a browser.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public abstract class CreatorBrowser {

    /** The required name of the creator or the browser. */
    @Expose
    private String name;

    /**
     * <p>
     * The default constructor.
     * </p>
     */
    public CreatorBrowser() {
    }

    /**
     * <p>
     * Returns the name of the creator or the browser.
     * </p>
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * Sets the name of the creator or the browser.
     * </p>
     *
     * @param name the name of the creator or browser.
     */
    public void setName(final String name) {
        this.name = name;
    }

}
