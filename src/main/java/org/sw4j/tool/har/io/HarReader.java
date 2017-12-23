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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.time.OffsetDateTime;
import org.sw4j.gson.extension.OffsetDateTimeAdapter;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;

/**
 * <p>
 * A reader that reads HAR JSON from a reader and returns the parsed model.
 * </p>
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class HarReader {

    /** The source of the HAR JSON model. */
    private final Reader source;

    /**
     * <p>
     * A constructor that takes a reader for this HAR reader.
     * </p>
     *
     * @param reader the reader that will be used for parsing.
     */
    public HarReader(final Reader reader) {
        this.source = reader;
    }

    /**
     * <p>
     * Reads in the JSON from the reader and returns a model from the parsed JSON.
     * </p>
     *
     * @param check whether to check the JSON for all required attributes.
     * @return the log that is parsed from the JSON.
     * @throws AttributeRequiredException if {@code check} is set and a required attribute is not set.
     */
    public Log read(final boolean check) throws AttributeRequiredException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .create();
        Har har = gson.fromJson(source, Har.class);
        if (check) {
            HarValidator.checkRequiredAttributes(har);
        }
        return har.getLog();
    }

}
