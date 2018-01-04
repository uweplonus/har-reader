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
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class PageTimings {

    /** Time (in milliseconds) from start of page loading until the page is loaded. */
    @Expose
    private BigDecimal onContentLoad;

    /** The default constructor. */
    public PageTimings() { }

    /**
     * <p>
     * Returns the time (in milliseconds) from the start of page loading until the page is loaded.
     * </p>
     *
     * @return the time (in milliseconds).
     */
    public BigDecimal getOnContentLoad() {
        return onContentLoad;
    }

    /**
     * <p>
     * Sets the time (in milliseconds) from the start of page loading until the page is loaded.
     * </p>
     *
     * @param onContentLoad the time (in milliseconds).
     */
    public void setOnContentLoad(final BigDecimal onContentLoad) {
        this.onContentLoad = onContentLoad;
    }

}
