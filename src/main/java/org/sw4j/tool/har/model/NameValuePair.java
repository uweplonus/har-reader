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
package org.sw4j.tool.har.model;

import com.google.gson.annotations.Expose;

/**
 * <p>
 * This abstract class represents a name-value pair.
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 * @see Header
 * @see QueryString
 */
public abstract class NameValuePair {

    /** The name of the name-value pair. */
    @Expose
    private String name;

    /** The value of the name-value pair. */
    @Expose
    private String value;

    /** The default constructor. */
    public NameValuePair() { }

    /**
     * <p>
     * Returns the name of the name-value pair.
     * </p>
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * Sets the name of the name-value pair.
     * </p>
     *
     * @param name the name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>
     * Returns the value of the name-value pair.
     * </p>
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * <p>
     * Sets the value of the name-value pair.
     * </p>
     *
     * @param value the value.
     */
    public void setValue(final String value) {
        this.value = value;
    }

}
