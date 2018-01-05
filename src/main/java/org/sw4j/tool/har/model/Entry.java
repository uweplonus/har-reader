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
 * This is an entry object of the log.
 * </p>
 * <p>
 * This class is not thread safe.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Entry {

    /** The optional pageref of the entry. */
    @Expose
    private String pageref;

    /** The default constructor. */
    public Entry() { }

    /**
     * <p>
     * Returns the optional pageref of the entry.
     * </p>
     *
     * @return the pageref of the entry.
     */
    public String getPageref() {
        return pageref;
    }

    /**
     * <p>
     * Sets the optional pageref of the entry.
     * </p>
     *
     * @param pageref the pageref of the entry.
     */
    public void setPageref(final String pageref) {
        this.pageref = pageref;
    }

}
