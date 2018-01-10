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

    /** The headers sent with the request. */
    @Expose
    private List<Header> headers;

    /** The query string sent with the request. */
    @Expose
    private List<QueryString> queryString;

    /** The optional posted data sent with the requrest. */
    @Expose
    private PostData postData;

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
     * @return an unmodifiable list with all cookies or {@code null} if the cookies are {@code null}.
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
     * @return the size of the list of cookies. If there are no cookies then {@code 0} is returned.
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
     * @return the cookie at the given index or {@code null} if either the given index is invalid or no cookies are
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

    /**
     * Clears the headers that means that the headers are set to {@code null}.
     */
    public void clearHeaders() {
        headers = null;
    }

    /**
     * Creates an empty headers list.
     */
    public void createEmptyHeaders() {
        headers = new LinkedList<>();
    }

    /**
     * <p>
     * Returns all headers as unmodifiable list.
     * </p>
     *
     * @return an unmodifiable list with all headers or {@code null} if headers are {@code null}.
     */
    public List<Header> getHeaders() {
        if (headers == null) {
            return headers;
        }
        return Collections.unmodifiableList(headers);
    }

    /**
     * <p>
     * Returns the size of the list of headers.
     * </p>
     *
     * @return the size of the list of headers. If there are no headers then {@code 0} is returned.
     */
    public int getHeadersSize() {
        if (headers != null) {
            return headers.size();
        }
        return 0;
    }

    /**
     * <p>
     * Returns the header at index {@code i}.
     * </p>
     *
     * @param i the index.
     * @return the header at the given index or {@code null} if either the given index is invalid or no headers are
     *     available.
     */
    public Header getHeader(final int i) {
        if (headers == null || i < 0 || i >= headers.size()) {
            return null;
        }
        return headers.get(i);
    }

    /**
     * <p>
     * Adds the given header to the list of headers.
     * </p>
     *
     * @param header the header to add to the list.
     */
    public void addHeader(final Header header) {
        if (headers == null) {
            headers = new LinkedList<>();
        }
        headers.add(header);
    }

    /**
     * Clears the query string that means that the query string are set to {@code null}.
     */
    public void clearQueryStrings() {
        queryString = null;
    }

    /**
     * Creates an empty query string list.
     */
    public void createEmptyQueryStrings() {
        queryString = new LinkedList<>();
    }

    /**
     * <p>
     * Returns the query string as unmodifiable list.
     * </p>
     *
     * @return an unmodifiable list with the query string or {@code null} if the query string are {@code null}.
     */
    public List<QueryString> getQueryStrings() {
        if (queryString == null) {
            return queryString;
        }
        return Collections.unmodifiableList(queryString);
    }

    /**
     * <p>
     * Returns the size of the list of the query string.
     * </p>
     *
     * @return the size of the list of the query string. If there is no query string then {@code 0} is returned.
     */
    public int getQueryStringsSize() {
        if (queryString != null) {
            return queryString.size();
        }
        return 0;
    }

    /**
     * <p>
     * Returns the query string at index {@code i}.
     * </p>
     *
     * @param i the index.
     * @return the query string at the given index or {@code null} if either the given index is invalid or no query
     *     string is available.
     */
    public QueryString getQueryString(final int i) {
        if (queryString == null || i < 0 || i >= queryString.size()) {
            return null;
        }
        return queryString.get(i);
    }

    /**
     * <p>
     * Adds the given query string to the list of query strings.
     * </p>
     *
     * @param qs the query string to add to the list.
     */
    public void addQueryString(final QueryString qs) {
        if (queryString == null) {
            queryString = new LinkedList<>();
        }
        queryString.add(qs);
    }

    /**
     * <p>
     * Returns the optional posted data of the request.
     * </p>
     *
     * @return the posted data of the request.
     */
    public PostData getPostData() {
        return postData;
    }

    /**
     * <p>
     * Sets the optional posted data of the request.
     * </p>
     *
     * @param postData the posted data of the request.
     */
    public void setPostData(final PostData postData) {
        this.postData = postData;
    }

}
