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
 * <p>
 * This class is not thread safe.
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

    /** The entries in the HAR. */
    @Expose
    private List<Entry> entries;

    /** Comment to the log. */
    @Expose
    private String comment;

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
     * Clears the pages that means that the pages are set to {@code null}.
     */
    public void clearPages() {
        pages = null;
    }

    /**
     * Creates an empty page list.
     */
    public void createEmptyPages() {
        pages = new LinkedList<>();
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
     * @return the size of the list of the pages. If there are no pages then {@code 0} is returned.
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

    /**
     * Clears the entries that means that the entries are set to {@code null}.
     */
    public void clearEntries() {
        entries = null;
    }

    /**
     * Creates an empty entry list.
     */
    public void createEmptyEntries() {
        entries = new LinkedList<>();
    }

    /**
     * <p>
     * Returns all entries as unmodifiable list.
     * </p>
     *
     * @return an unmodifiable list with all entries or {@code null} if there are no entries.
     */
    public List<Entry> getEntries() {
        if (entries == null) {
            return entries;
        }
        return Collections.unmodifiableList(entries);
    }

    /**
     * <p>
     * Returns the size of the list of entries.
     * </p>
     *
     * @return the size of the list of entries. If there are no entries then {@code 0} is returned.
     */
    public int getEntriesSize() {
        if (entries != null) {
            return entries.size();
        }
        return 0;
    }

    /**
     * <p>
     * Returns the entry at index {@code i}.
     * </p>
     *
     * @param i the index.
     * @return the entry at the given index or {@code null} if either the given index is invalid or no entries are
     *     available.
     */
    public Entry getEntry(final int i) {
        if (entries == null || i < 0 || i >= entries.size()) {
            return null;
        }
        return entries.get(i);
    }

    /**
     * <p>
     * Adds the given entry to the list of entries.
     * </p>
     *
     * @param entry the entry to add to the list.
     */
    public void addEntry(final Entry entry) {
        if (entries == null) {
            entries = new LinkedList<>();
        }
        entries.add(entry);
    }

    /**
     * <p>
     * Returns the comment to the log.
     * </p>
     *
     * @return the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * <p>
     * Sets the comment to the log.
     * </p>
     *
     * @param comment the comment.
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

}
