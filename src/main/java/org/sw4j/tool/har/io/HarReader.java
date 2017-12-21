package org.sw4j.tool.har.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
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
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Har har = gson.fromJson(source, Har.class);
        if (check) {
            HarValidator.checkRequiredAttributes(har);
        }
        return har.getLog();
    }

}
