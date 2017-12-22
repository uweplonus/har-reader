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
 * This is the log object of the HAR.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Log {

    /** The version of the HAR. */
    @Expose
    private String version;

    /** The creator of the HAR. */
    @Expose
    private Creator creator;

    /** The optional browser of the HAR. */
    @Expose
    private Browser browser;

    /** The optional list of pages in the HAR. */
    @Expose
    private List<Page> pages;

    /** The default constructor. */
    public Log() { }

    /**
     * <p>
     * Returns the version of the HAR.
     * </p>
     *
     * @return the version of the HAR.
     */
    public String getVersion() {
        return version;
    }

    /**
     * <p>
     * Sets the version of the HAR.
     * </p>
     *
     * @param version the version of the HAR.
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * <p>
     * Returns the creator of the HAR.
     * </p>
     *
     * @return the creator of the HAR.
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * <p>
     * Sets the creator of the HAR.
     * </p>
     *
     * @param creator the creator of the HAR.
     */
    public void setCreator(final Creator creator) {
        this.creator = creator;
    }

    /**
     * <p>
     * Returns the browser of the HAR.
     * </p>
     *
     * @return the browser of the HAR.
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * <p>
     * Sets the browser of the HAR.
     * </p>
     *
     * @param browser the browser of the HAR.
     */
    public void setBrowser(final Browser browser) {
        this.browser = browser;
    }

    /**
     * <p>
     * Returns all pages as unmodifiable list.
     * </p>
     *
     * @return an unmodifiable list with all pages or {@code null} if there are no pages.
     */
    public List<Page> getPages() {
        if (pages == null) {
            return pages;
        }
        return Collections.unmodifiableList(pages);
    }

    /**
     * <p>
     * Returns the size of the list of pages.
     * </p>
     *
     * @return the size of the list of the pages. If there are no pages then {@code 0} if returned.
     */
    public int getPagesSize() {
        if (pages != null) {
            return pages.size();
        }
        return 0;
    }

    /**
     * <p>
     * Returns the page at index {@code i}.
     * </p>
     *
     * @param i the index.
     * @return the page at the given index or {@code null} if either the given index is invalid or no pages are
     *     available.
     */
    public Page getPage(final int i) {
        if (pages == null || i < 0 || i >= pages.size()) {
            return null;
        }
        return pages.get(i);
    }

    /**
     * <p>
     * Adds the given page to the list of pages.
     * </p>
     *
     * @param page the page to add to the list.
     */
    public void addPage(final Page page) {
        if (pages == null) {
            pages = new LinkedList<>();
        }
        pages.add(page);
    }

}
