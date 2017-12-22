package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

/**
 * <p>
 * This model class represents either a creator or a browser.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class CreatorBrowser {

    /** The type that this object represents. */
    private final Type type;

    /** The required name of the creator or the browser. */
    @Expose
    private String name;

    /**
     * <p>
     * The constructor with a type argument.
     * </p>
     *
     * @param type the type of this object
     */
    public CreatorBrowser(final Type type) {
        this.type = type;
    }

    /**
     * <p>
     * Returns the type of this object.
     * </p>
     *
     * @return the type of this object.
     */
    public Type getType() {
        return type;
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


    /**
     * Enum to define the type of the enclosing object.
     */
    public static enum Type {

        /** The object is a creator. */
        CREATOR,
        /** The object is a browser. */
        BROWSER,
        ;

    }

}
