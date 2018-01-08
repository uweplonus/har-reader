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
import java.time.OffsetDateTime;

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

    /** The optional path of the cookie. */
    @Expose
    private String path;

    /** The optional domain of the cookie. */
    @Expose
    private String domain;

    /** The optional date and time when the cookie expires. */
    @Expose
    private OffsetDateTime expires;

    /** The optional indication if the cookie is HTTP only. */
    @Expose
    private Boolean httpOnly;

    /** The optional indication if the cookie is secure. */
    @Expose
    private Boolean secure;

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

    /**
     * <p>
     * Returns the optional path of the cookie.
     * </p>
     *
     * @return the path of the cookie.
     */
    public String getPath() {
        return path;
    }

    /**
     * <p>
     * Sets the optional path of the cookie.
     * </p>
     *
     * @param path the path of the cookie.
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * <p>
     * Returns the optional domain of the cookie.
     * </p>
     *
     * @return the domain of the cookie.
     */
    public String getDomain() {
        return domain;
    }

    /**
     * <p>
     * Sets the optional domain of the cookie.
     * </p>
     *
     * @param domain the domain of the cookie.
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * <p>
     * Returns the optional date and time when the cookie expires.
     * </p>
     *
     * @return the date and time when the cookie expires.
     */
    public OffsetDateTime getExpires() {
        return expires;
    }

    /**
     * <p>
     * Sets the optional date and time when the cookie expires.
     * </p>
     *
     * @param expires the date and time of the cookie expires.
     */
    public void setExpires(final OffsetDateTime expires) {
        this.expires = expires;
    }

    /**
     * <p>
     * Returns the optional indication if the cookie is HTTP only.
     * </p>
     *
     * @return the indication for HTTP only cookies.
     */
    public Boolean getHttpOnly() {
        return httpOnly;
    }

    /**
     * <p>
     * Sets the optional indication if the cookie is HTTP only.
     * </p>
     *
     * @param httpOnly the indication for HTTP only cookies.
     */
    public void setHttpOnly(final Boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    /**
     * <p>
     * Returns the optional indication if the cookie is secure.
     * </p>
     *
     * @return the indication for secure cookies.
     */
    public Boolean getSecure() {
        return secure;
    }

    /**
     * <p>
     * Sets the optional indication if the cookie is secure.
     * </p>
     *
     * @param secure the indication for secure cookies.
     */
    public void setSecure(final Boolean secure) {
        this.secure = secure;
    }

}
