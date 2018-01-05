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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * This is a request object of the entry.
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Request {

    /** The method of the request. */
    @Expose
    private String method;

    /** The URL of the request. */
    @Expose
    private String url;

    /** The HTTP version of the request. */
    @Expose
    private String httpVersion;

    /** The cookies sent with the request. */
    @Expose
    private List<Cookie> cookies;

    /** The default constructor. */
    public Request() { }

    /**
     * <p>
     * Returns the method of the request.
     * </p>
     *
     * @return the method of the request.
     */
    public String getMethod() {
        return method;
    }

    /**
     * <p>
     * Sets the method of the request.
     * </p>
     *
     * @param method the method of the request.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * <p>
     * Returns the URL of the request.
     * </p>
     *
     * @return the URL of the request.
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>
     * Sets the URL of the request.
     * </p>
     *
     * @param url the URL of the request.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * <p>
     * Returns the HTTP version of the request.
     * </p>
     *
     * @return the HTTP version of the request.
     */
    public String getHttpVersion() {
        return httpVersion;
    }

    /**
     * <p>
     * Sets the HTTP version of the request.
     * </p>
     *
     * @param httpVersion the HTTP version of the request.
     */
    public void setHttpVersion(final String httpVersion) {
        this.httpVersion = httpVersion;
    }

    /**
     * Clears the cookies that means that the cookies are set to {@code null}.
     */
    public void clearCookies() {
        cookies = null;
    }

    /**
     * Creates an empty cookie list.
     */
    public void createEmptyCookies() {
        cookies = new LinkedList<>();
    }

    /**
     * <p>
     * Returns all cookies as unmodifiable list.
     * </p>
     *
     * @return an unmodifiable list with all cookies or {@code null} if there are no cookies.
     */
    public List<Cookie> getCookies() {
        if (cookies == null) {
            return cookies;
        }
        return Collections.unmodifiableList(cookies);
    }

    /**
     * <p>
     * Returns the size of the list of cookies.
     * </p>
     *
     * @return the size of the list of cookies. If there are no entries then {@code 0} is returned.
     */
    public int getCookiesSize() {
        if (cookies != null) {
            return cookies.size();
        }
        return 0;
    }

    /**
     * <p>
     * Returns the cookie at index {@code i}.
     * </p>
     *
     * @param i the index.
     * @return the cookie at the given index or {@code null} if either the given index is invalid or no entries are
     *     available.
     */
    public Cookie getCookie(final int i) {
        if (cookies == null || i < 0 || i >= cookies.size()) {
            return null;
        }
        return cookies.get(i);
    }

    /**
     * <p>
     * Adds the given cookie to the list of cookies.
     * </p>
     *
     * @param cookie the cookie to add to the list.
     */
    public void addCookie(final Cookie cookie) {
        if (cookies == null) {
            cookies = new LinkedList<>();
        }
        cookies.add(cookie);
    }

}
