package org.sw4j.tool.har.io;

/**
 * <p>
 * This exception is thrown when a required attribute is not set.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class AttributeRequiredException extends RuntimeException {

    /** The object that owns the required attribute. */
    private final String object;

    /** The attribute that is required but not set. */
    private final String attribute;

    /**
     * <p>
     * Constructor of this exception. As parameters it takes the object that owns the attribute and the name of the
     * attribute that is required.
     * </p>
     *
     * @param object the object of the attribute.
     * @param attribute the attribute that is required but missing.
     */
    public AttributeRequiredException(final String object, final String attribute) {
        super(new StringBuilder("The object \"").append(object).append("\" requires the attribute \"")
                .append(attribute).append("\".").toString());
        this.object = object;
        this.attribute = attribute;
    }

    /**
     * <p>
     * Returns the object of the attribute.
     * </p>
     *
     * @return the object of the attribute.
     */
    public String getObject() {
        return object;
    }

    /**
     * <p>
     * Returns the attribute that is required but missing.
     * </p>
     *
     * @return the attribute that is missing.
     */
    public String getAttribute() {
        return attribute;
    }

}
