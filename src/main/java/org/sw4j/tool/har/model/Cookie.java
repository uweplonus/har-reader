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
 * This is a cookie object of the request or response.
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Cookie {

    /** The name of the cookie. */
    @Expose
    private String name;

    /** The value of the cookie. */
    @Expose
    private String value;

    /** The default constructor. */
    public Cookie() { }

    /**
     * <p>
     * Returns the name of the cookie.
     * </p>
     *
     * @return the name of the cookie.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * Sets the name of the cookie.
     * </p>
     *
     * @param name the name of the cookie.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>
     * Returns the value of the cookie.
     * </p>
     *
     * @return the value of the cookie.
     */
    public String getValue() {
        return value;
    }

    /**
     * <p>
     * Sets the value of the cookie.
     * </p>
     *
     * @param value the value of the cookie.
     */
    public void setValue(final String value) {
        this.value = value;
    }

}
