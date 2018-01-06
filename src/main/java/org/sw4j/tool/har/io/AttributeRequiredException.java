/*
 * Copyright 2017 Uwe Plonus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sw4j.tool.har.io;

import java.util.Objects;

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

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttributeRequiredException that = (AttributeRequiredException)o;
        return Objects.equals(getObject(), that.getObject()) &&
                Objects.equals(getAttribute(), that.getAttribute());
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(getObject(), getAttribute());
    }

}
