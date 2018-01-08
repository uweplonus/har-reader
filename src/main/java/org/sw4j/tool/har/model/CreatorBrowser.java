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
 * This model class represents either a creator or a browser.
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @see Creator
 * @see Browser
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public abstract class CreatorBrowser {

    /** The required name of the creator or the browser. */
    @Expose
    private String name;

    /** The required version of the creator or the browser. */
    @Expose
    private String version;

    /** The optional comment of the creator or the browser. */
    @Expose
    private String comment;

    /**
     * <p>
     * The default constructor.
     * </p>
     */
    public CreatorBrowser() {
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
     * <p>
     * Returns the version of the creator or the browser.
     * </p>
     *
     * @return the version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * <p>
     * Sets the version of the creator or the browser.
     * </p>
     *
     * @param version the version of the creator or browser.
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * <p>
     * Returns the comment of the creator or the browser.
     * </p>
     *
     * @return the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * <p>
     * Sets the comment of the creator or the browser.
     * </p>
     *
     * @param comment the comment of the creator or browser.
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

}
