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
 * This is an request object of the entry.
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

}
