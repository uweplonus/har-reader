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
 * This is a page object of the log.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Page {

    /** The date and time when the page load started. */
    @Expose
    private OffsetDateTime startedDateTime;

    /** The unique identifier of the page. */
    @Expose
    private String id;

    /** The title of the page. */
    @Expose
    private String title;

    /** The page timings of the page. */
    @Expose
    private PageTimings pageTimings;

    /** The default constructor. */
    public Page() { }

    /**
     * <p>
     * Returns the date and time when the page load started.
     * </p>
     *
     * @return the date and time of the page load.
     */
    public OffsetDateTime getStartedDateTime() {
        return startedDateTime;
    }

    /**
     * <p>
     * Sets the date and time when the page load started.
     * </p>
     *
     * @param startedDateTime the date and time of the page load.
     */
    public void setStartedDateTime(final OffsetDateTime startedDateTime) {
        this.startedDateTime = startedDateTime;
    }

    /**
     * <p>
     * Returns the unique identifier of the page within the log.
     * </p>
     *
     * @return the unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * <p>
     * Sets the unique identifier of the page within the log.
     * </p>
     *
     * @param id the unique identifier.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * <p>
     * Returns the title of the page.
     * </p>
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>
     * Sets the title of the page.
     * </p>
     *
     * @param title the title.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * <p>
     * Returns the timings of this page.
     * </p>
     *
     * @return the timings of this page.
     */
    public PageTimings getPageTimings() {
        return pageTimings;
    }

    /**
     * <p>
     * Sets the timings of this page.
     * </p>
     *
     * @param pageTimings the timings of this page.
     */
    public void setPageTimings(final PageTimings pageTimings) {
        this.pageTimings = pageTimings;
    }

}
