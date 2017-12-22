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
import org.sw4j.tool.har.model.CreatorBrowser;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;

/**
 * The {@code HarValidator} provides methods to validate the HAR model.
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public final class HarValidator {

    /**
     * Hidden constructor for an utility class.
     */
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
    public static List<RequiredAttribute> getMissingRequiredAttributes(final Har har) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (har == null) {
            result.add(new RequiredAttribute("", "har"));
        } else {
            result.addAll(getMissingRequiredAttributes(har.getLog()));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the log object.
     * </p>
     *
     * @param log    the log object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingRequiredAttributes(final Log log) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (log == null) {
            result.add(new RequiredAttribute("", "log"));
        } else {
            if (log.getVersion() == null) {
                result.add(new RequiredAttribute("log", "version"));
            }
            result.addAll(getMissingRequiredAttributes(log.getCreator(), CreatorBrowser.Type.CREATOR));
            result.addAll(getMissingRequiredAttributes(log.getBrowser(), CreatorBrowser.Type.BROWSER));
        }
        return result;
    }

    /**
     * <p>
     * Return all missing required attributes from the creator or browser object.
     * </p>
     *
     * @param creatorBrowser the creator or browser object to check.
     * @return a list containing all required but missing attributes.
     */
    private static List<RequiredAttribute> getMissingRequiredAttributes(final CreatorBrowser creatorBrowser,
            final CreatorBrowser.Type type) {
        List<RequiredAttribute> result = new LinkedList<>();
        if (creatorBrowser == null) {
            if (type == type.CREATOR){
                result.add(new RequiredAttribute("log", "creator"));
            }
        } else {
            String parent = "log.creator";
            if (type == CreatorBrowser.Type.BROWSER) {
                parent = "log.browser";
            }
            if (creatorBrowser.getName() == null) {
                result.add(new RequiredAttribute(parent, "name"));
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
        List<RequiredAttribute> missingAttributes = getMissingRequiredAttributes(har);
        if (!missingAttributes.isEmpty()) {
            RequiredAttribute firstEntry = missingAttributes.get(0);
            throw new AttributeRequiredException(firstEntry.getParent(), firstEntry.getAttribute());
        }
    }


    /**
     * An immutable class to return required attributes from the model.
     */
    public static class RequiredAttribute {

        /**
         * The parent of the required attribute.
         */
        private final String parent;

        /**
         * The attribute.
         */
        private final String attribute;

        /**
         * <p>
         * Constructor of the immutable class.
         * </p>
         *
         * @param parent    the parent object of the attribute.
         * @param attribute the attribute.
         */
        public RequiredAttribute(final String parent, final String attribute) {
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
            return parent;
        }

        /**
         * <p>
         * Returns the attribute.
         * </p>
         *
         * @return the attribute.
         */
        public String getAttribute() {
            return attribute;
        }

    }

}
