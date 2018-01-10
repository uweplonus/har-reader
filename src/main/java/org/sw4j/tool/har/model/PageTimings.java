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
import java.math.BigDecimal;

/**
 * <p>
 * This is a page timings object of a page in the log.
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class PageTimings {

    /** Time (in milliseconds) from start of page loading until the content is loaded. */
    @Expose
    private BigDecimal onContentLoad;

    /** Time (in milliseconds) from start of page loading until the page is loaded. */
    @Expose
    private BigDecimal onLoad;

    /** Comment to the page timings. */
    @Expose
    private String comment;

    /** The default constructor. */
    public PageTimings() { }

    /**
     * <p>
     * Returns the time (in milliseconds) from the start of page loading until the content is loaded.
     * </p>
     *
     * @return the time (in milliseconds).
     */
    public BigDecimal getOnContentLoad() {
        return onContentLoad;
    }

    /**
     * <p>
     * Sets the time (in milliseconds) from the start of page loading until the content is loaded.
     * </p>
     *
     * @param onContentLoad the time (in milliseconds).
     */
    public void setOnContentLoad(final BigDecimal onContentLoad) {
        this.onContentLoad = onContentLoad;
    }

    /**
     * <p>
     * Returns the time (in milliseconds) from the start of page loading until the page is loaded.
     * </p>
     *
     * @return the time (in milliseconds).
     */
    public BigDecimal getOnLoad() {
        return onLoad;
    }

    /**
     * <p>
     * Sets the time (in milliseconds) from the start of page loading until the page is loaded.
     * </p>
     *
     * @param onLoad the time (in milliseconds).
     */
    public void setOnLoad(final BigDecimal onLoad) {
        this.onLoad = onLoad;
    }

    /**
     * <p>
     * Returns the comment to the page timings.
     * </p>
     *
     * @return the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * <p>
     * Sets the comment to the page timings.
     * </p>
     *
     * @param comment the comment.
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

}
