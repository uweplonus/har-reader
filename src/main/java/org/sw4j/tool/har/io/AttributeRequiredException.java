package org.sw4j.tool.har.io;

public class AttributeRequiredException extends RuntimeException {

    private final String object;

    private final String attribute;

    public AttributeRequiredException(String object, String attribute) {
        super(new StringBuilder("The object \"").append(object).append("\" requires the attribute \"")
                .append(attribute).append("\".").toString());
        this.object = object;
        this.attribute = attribute;
    }

    public String getObject() {
        return object;
    }

    public String getAttribute() {
        return attribute;
    }

}
