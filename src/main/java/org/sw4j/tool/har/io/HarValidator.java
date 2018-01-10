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
package org.sw4j.tool.har.io;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.sw4j.tool.har.model.Browser;
import org.sw4j.tool.har.model.Cookie;
import org.sw4j.tool.har.model.Creator;
import org.sw4j.tool.har.model.CreatorBrowser;
import org.sw4j.tool.har.model.Entry;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Header;
import org.sw4j.tool.har.model.Log;
import org.sw4j.tool.har.model.Page;
import org.sw4j.tool.har.model.PageTimings;
import org.sw4j.tool.har.model.QueryString;
import org.sw4j.tool.har.model.Request;

/**
 * The {@code HarValidator} provides methods to validate the HAR model.
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public final class HarValidator {

    /** Hidden constructor for an utility class. */
    private HarValidator() {
    }

    /**
     * <p>
     * Return all missing required attributes from the HAR model in a list.
     * </p>
     * <p>
     * If the given parameter ({@code har}) is {@code null} then a list with only a single element is returned and the
     * parent object is {@code ""} (an empty String) and the missing attribute is {@code "har"}.
     * </p>
     *
     * @param har the model to check for missing attributes.
     * @return a list with all missing required attributes. If no attributes are missing the an empty list will be
     *   returned.
     */
    public static List<RequiredAttribute> getMissingAttributes(final Har har) {
        List<RequiredAttribute> result = new LinkedList<>();
        String parent = "";
        if (har == null) {
            result.add(new RequiredAttribute(parent, "har"));
        } else {
            result.addAll(getMissingLogAttributes(parent, har.getLog()));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the log object.
     * </p>
     *
     * @param parent the parent of the log object to check.
     * @param log the log object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingLogAttributes(final CharSequence parent, final Log log) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (log == null) {
            result.add(new RequiredAttribute(parent, "log"));
        } else {
            StringBuilder newParent = createNewParent(parent, "log");
            if (log.getVersion() == null) {
                result.add(new RequiredAttribute(newParent, "version"));
            }
            result.addAll(getMissingCreatorAttributes(newParent, log.getCreator()));
            result.addAll(getMissingBrowserAttributes(newParent, log.getBrowser()));
            result.addAll(getMissingPagesAttributes(newParent, log.getPages()));
            result.addAll(getMissingEntriesAttributes(newParent, log.getEntries()));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the creator.
     * </p>
     *
     * @param parent the parent of the creator object to check.
     * @param creator the creator object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingCreatorAttributes(final CharSequence parent,
            final Creator creator) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (creator == null) {
            result.add(new RequiredAttribute(parent, "creator"));
        } else {
            StringBuilder newParent = createNewParent(parent, "creator");
            result.addAll(getMissingCreatorBrowserAttributes(newParent, creator));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the browser object.
     * </p>
     *
     * @param parent the parent of the browser object to check.
     * @param browser the browser object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingBrowserAttributes(final CharSequence parent,
            final Browser browser) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (browser != null) {
            StringBuilder newParent = createNewParent(parent, "browser");
            result.addAll(getMissingCreatorBrowserAttributes(newParent, browser));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the creator or browser object.
     * </p>
     *
     * @param parent the parent object that represents the given creator or browser.
     * @param creatorBrowser the creator or browser object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingCreatorBrowserAttributes(final CharSequence parent,
            final CreatorBrowser creatorBrowser) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (creatorBrowser.getName() == null) {
            result.add(new RequiredAttribute(parent, "name"));
        }
        if (creatorBrowser.getVersion() == null) {
            result.add(new RequiredAttribute(parent, "version"));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the list of pages.
     * </p>
     *
     * @param parent the parent of the pages object to check.
     * @param pages the list of pages to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingPagesAttributes(final CharSequence parent,
            final List<Page> pages) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (pages != null) {
            for (int i = 0; i < pages.size(); i++) {
                StringBuilder indexedPage = new StringBuilder("pages[").append(i).append(']');
                StringBuilder newParent = createNewParent(parent, indexedPage);
                result.addAll(getMissingPageAttributes(newParent, pages.get(i)));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the page object.
     * </p>
     *
     * @param parent the parent of the page object to check.
     * @param page the page object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingPageAttributes(final CharSequence parent, final Page page) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (page != null) {
            if (page.getStartedDateTime() == null) {
                result.add(new RequiredAttribute(parent, "startedDateTime"));
            }
            if (page.getId() == null) {
                result.add(new RequiredAttribute(parent, "id"));
            }
            if (page.getTitle() == null) {
                result.add(new RequiredAttribute(parent, "title"));
            }
            result.addAll(getMissingPageTimingsAttributes(parent, page.getPageTimings()));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the page timings object.
     * </p>
     *
     * @param parent the parent of the page timings object.
     * @param pageTimings the page timings object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingPageTimingsAttributes(final CharSequence parent,
            final PageTimings pageTimings) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (pageTimings == null) {
            result.add(new RequiredAttribute(parent, "pageTimings"));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the list of entries.
     * </p>
     *
     * @param parent the parent of the entries object.
     * @param entries the list of entries to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingEntriesAttributes(final CharSequence parent,
            final List<Entry> entries) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (entries == null) {
            result.add(new RequiredAttribute(parent, "entries"));
        } else {
            for (int i = 0; i < entries.size(); i++) {
                StringBuilder indexedPage = new StringBuilder("entries[").append(i).append(']');
                StringBuilder newParent = createNewParent(parent, indexedPage);
                result.addAll(getMissingEntryAttributes(newParent, entries.get(i)));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the entry object.
     * </p>
     *
     * @param parent the parent of the entry object.
     * @param entry the entry object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingEntryAttributes(final CharSequence parent, final Entry entry) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (entry != null) {
            if (entry.getStartedDateTime() == null) {
                result.add(new RequiredAttribute(parent, "startedDateTime"));
            }
            if (entry.getTime() == null) {
                result.add(new RequiredAttribute(parent, "time"));
            }
            result.addAll(getMissingRequestAttributes(parent, entry.getRequest()));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the request object.
     * </p>
     *
     * @param parent the parent of the request object.
     * @param request the request object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingRequestAttributes(final CharSequence parent,
            final Request request) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (request == null) {
            result.add(new RequiredAttribute(parent, "request"));
        } else {
            StringBuilder newParent = createNewParent(parent, "request");
            if (request.getMethod() == null) {
                result.add(new RequiredAttribute(newParent, "method"));
            }
            if (request.getUrl() == null) {
                result.add(new RequiredAttribute(newParent, "url"));
            }
            if (request.getHttpVersion() == null) {
                result.add(new RequiredAttribute(newParent, "httpVersion"));
            }
            result.addAll(getMissingCookiesAttributes(newParent, request.getCookies()));
            result.addAll(getMissingHeadersAttributes(newParent, request.getHeaders()));
            result.addAll(getMissingQueryStringsAttributes(newParent, request.getQueryStrings()));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the list of cookies.
     * </p>
     *
     * @param parent the parent of the cookies object.
     * @param cookies the list of cookies to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingCookiesAttributes(final CharSequence parent,
            final List<Cookie> cookies) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (cookies == null) {
            result.add(new RequiredAttribute(parent, "cookies"));
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                StringBuilder indexedPage = new StringBuilder("cookies[").append(i).append(']');
                StringBuilder newParent = createNewParent(parent, indexedPage);
                result.addAll(getMissingCookieAttributes(newParent, cookies.get(i)));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the cookie object.
     * </p>
     *
     * @param parent the parent of the cookie object.
     * @param cookie the cookie object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingCookieAttributes(final CharSequence parent, final Cookie cookie) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (cookie != null) {
            if (cookie.getName() == null) {
                result.add(new RequiredAttribute(parent, "name"));
            }
            if (cookie.getValue() == null) {
                result.add(new RequiredAttribute(parent, "value"));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the list of headers.
     * </p>
     *
     * @param parent the parent of the headers object.
     * @param headers the list of headers to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingHeadersAttributes(final CharSequence parent,
            final List<Header> headers) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (headers == null) {
            result.add(new RequiredAttribute(parent, "headers"));
        } else {
            for (int i = 0; i < headers.size(); i++) {
                StringBuilder indexedPage = new StringBuilder("headers[").append(i).append(']');
                StringBuilder newParent = createNewParent(parent, indexedPage);
                result.addAll(getMissingHeaderAttributes(newParent, headers.get(i)));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the header object.
     * </p>
     *
     * @param parent the parent of the header object.
     * @param header the header object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingHeaderAttributes(final CharSequence parent, final Header header) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (header != null) {
            if (header.getName() == null) {
                result.add(new RequiredAttribute(parent, "name"));
            }
            if (header.getValue() == null) {
                result.add(new RequiredAttribute(parent, "value"));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the list of query strings.
     * </p>
     *
     * @param parent the parent of the queryStrings object.
     * @param queryStrings the list of query strings to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingQueryStringsAttributes(final CharSequence parent,
            final List<QueryString> queryStrings) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (queryStrings == null) {
            result.add(new RequiredAttribute(parent, "queryString"));
        } else {
            for (int i = 0; i < queryStrings.size(); i++) {
                StringBuilder indexedPage = new StringBuilder("queryString[").append(i).append(']');
                StringBuilder newParent = createNewParent(parent, indexedPage);
                result.addAll(getMissingQueryStringAttributes(newParent, queryStrings.get(i)));
            }
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the query string object.
     * </p>
     *
     * @param parent the parent of the query string object.
     * @param queryString the query string object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingQueryStringAttributes(final CharSequence parent,
            final QueryString queryString) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (queryString != null) {
            if (queryString.getName() == null) {
                result.add(new RequiredAttribute(parent, "name"));
            }
            if (queryString.getValue() == null) {
                result.add(new RequiredAttribute(parent, "value"));
            }
        }
        return result;
    }

    /**
     * <p>
     * Check for missing attributes from the HAR model and throw an exception when at least one attribute is missing.
     * The exception contains the information for the first missing attribute.
     * </p>
     *
     * @param har the model to check for missing attributes.
     * @throws AttributeRequiredException if at least one required attribute in the model is missing.
     */
    public static void checkRequiredAttributes(final Har har) throws AttributeRequiredException {
        List<RequiredAttribute> missingAttributes = getMissingAttributes(har);
        if (!missingAttributes.isEmpty()) {
            RequiredAttribute firstEntry = missingAttributes.get(0);
            throw new AttributeRequiredException(firstEntry.getParent(), firstEntry.getAttribute());
        }
    }

    /**
     * <p>
     * Create a new parent from the old parent and the new child. The new parent is the child appended to the old parent
     * separated by a dot ({@code .}). If the old parent is {@code null} or empty then no dot is prepended to the child.
     * </p>
     *
     * @param parent the old parent to use.
     * @param child the child to be appended.
     * @return the new parent consisting of the old parent and the child.
     */
    private static StringBuilder createNewParent(final CharSequence parent, final CharSequence child) {
        StringBuilder newParent = new StringBuilder();
        if (parent != null && !"".equals(parent)) {
            newParent.append(parent).append('.');
        }
        newParent.append(child);
        return newParent;
    }


    /**
     * An immutable class to return required attributes from the model.
     */
    public static class RequiredAttribute {

        /**
         * The parent of the required attribute.
         */
        private final CharSequence parent;

        /**
         * The attribute.
         */
        private final CharSequence attribute;

        /**
         * <p>
         * Constructor of the immutable class.
         * </p>
         *
         * @param parent    the parent object of the attribute.
         * @param attribute the attribute.
         */
        public RequiredAttribute(final CharSequence parent, final CharSequence attribute) {
            this.parent = parent;
            this.attribute = attribute;
        }

        /**
         * <p>
         * Returns the parent of the attribute.
         * </p>
         *
         * @return the parent.
         */
        public String getParent() {
            return parent.toString();
        }

        /**
         * <p>
         * Returns the attribute.
         * </p>
         *
         * @return the attribute.
         */
        public String getAttribute() {
            return attribute.toString();
        }

        /** {@inheritDoc} */
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RequiredAttribute that = (RequiredAttribute)o;
            return Objects.equals(getParent(), that.getParent()) &&
                    Objects.equals(getAttribute(), that.getAttribute());
        }

        /** {@inheritDoc} */
        @Override
        public int hashCode() {
            return Objects.hash(getParent(), getAttribute());
        }

        /** {@inheritDoc} */
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("RequiredAttribute{");
            sb.append("parent='").append(parent).append('\'');
            sb.append(", attribute='").append(attribute).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }

}
